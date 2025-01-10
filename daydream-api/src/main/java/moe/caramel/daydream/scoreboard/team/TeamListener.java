package moe.caramel.daydream.scoreboard.team;

import org.bukkit.OfflinePlayer;
import org.jspecify.annotations.NullMarked;

/**
 * {@link org.bukkit.scoreboard.Team}의 변경 사항을 리스닝합니다.
 */
@NullMarked
public interface TeamListener {

    /**
     * 팀이 제거되었을 때
     */
    default void unregister() {
    }

    /**
     * 엔트리가 팀에 추가되었을 때
     *
     * @param entry 엔트리
     */
    default void addEntry(final String entry) {
    }

    /**
     * 플레이어가 팀에 추가되었을 때
     * <br>
     * {@link #addEntry(String)}와 동시에 호출될 수 있습니다.
     *
     * @param player 플레이어
     */
    default void addPlayer(final OfflinePlayer player) {
    }

    /**
     * 엔트리가 팀에서 제거되었을 때
     *
     * @param entry 엔트리
     */
    default void removeEntry(final String entry) {
    }

    /**
     * 플레이어가 팀에서 제거되었을 때
     * <br>
     * {@link #addEntry(String)}와 동시에 호출될 수 있습니다.
     *
     * @param player 플레이어
     */
    default void removePlayer(final OfflinePlayer player) {
    }
}
