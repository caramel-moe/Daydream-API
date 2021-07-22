#!/usr/bin/env bash
# get base dir regardless of execution location
SOURCE="${BASH_SOURCE[0]}"
while [ -h "$SOURCE" ]; do # resolve $SOURCE until the file is no longer a symlink
    DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"
    SOURCE="$(readlink "$SOURCE")"
    [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
done
. $(dirname $SOURCE)/init.sh

PS1="$"
echo "Rebuilding patch files from current fork state..."
function savePatches {
    what=$1
    cd $basedir/$what/

    mkdir -p $basedir/patches
    if [ -d ".git/rebase-apply" ]; then
        # in middle of a rebase, be smarter
        echo "REBASE DETECTED - PARTIAL SAVE"
        last=$(cat ".git/rebase-apply/last")
        next=$(cat ".git/rebase-apply/next")
        declare -a files=("$basedir/patches/"*.patch)
        for i in $(seq -f "%04g" 1 1 $last)
        do
            if [ $i -lt $next ]; then
                rm "${files[`expr $i - 1`]}"
            fi
        done
    else
        rm $basedir/patches/*.patch
    fi

    git format-patch --no-signature --zero-commit --full-index --no-stat -N -o $basedir/patches upstream/upstream
    cd $basedir
    git add -A $basedir/patches
    echo "  Patches saved for $what to patches"
}

savePatches ${FORK_NAME}-API

$basedir/scripts/push.sh
