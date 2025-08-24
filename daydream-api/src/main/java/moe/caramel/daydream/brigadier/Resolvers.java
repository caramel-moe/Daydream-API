package moe.caramel.daydream.brigadier;

import static io.papermc.paper.command.brigadier.MessageComponentSerializer.message;
import static net.kyori.adventure.text.Component.translatable;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.google.common.collect.Range;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.AxisSet;
import io.papermc.paper.command.brigadier.argument.SignedMessageResolver;
import io.papermc.paper.command.brigadier.argument.position.ColumnBlockPosition;
import io.papermc.paper.command.brigadier.argument.position.ColumnFinePosition;
import io.papermc.paper.command.brigadier.argument.predicate.BlockInWorldPredicate;
import io.papermc.paper.command.brigadier.argument.predicate.ItemStackPredicate;
import io.papermc.paper.command.brigadier.argument.range.DoubleRangeProvider;
import io.papermc.paper.command.brigadier.argument.range.IntegerRangeProvider;
import io.papermc.paper.command.brigadier.argument.resolvers.AngleResolver;
import io.papermc.paper.command.brigadier.argument.resolvers.BlockPositionResolver;
import io.papermc.paper.command.brigadier.argument.resolvers.ColumnBlockPositionResolver;
import io.papermc.paper.command.brigadier.argument.resolvers.ColumnFinePositionResolver;
import io.papermc.paper.command.brigadier.argument.resolvers.FinePositionResolver;
import io.papermc.paper.command.brigadier.argument.resolvers.PlayerProfileListResolver;
import io.papermc.paper.command.brigadier.argument.resolvers.RotationResolver;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.EntitySelectorArgumentResolver;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import io.papermc.paper.entity.LookAnchor;
import io.papermc.paper.math.BlockPosition;
import io.papermc.paper.math.FinePosition;
import io.papermc.paper.math.Rotation;
import io.papermc.paper.registry.TypedKey;
import it.unimi.dsi.fastutil.ints.IntList;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.api.BinaryTagHolder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.GameMode;
import org.bukkit.HeightMap;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.jspecify.annotations.NullMarked;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * 인자 타입 리졸버
 *
 * @see io.papermc.paper.command.brigadier.argument.ArgumentTypes
 */
@NullMarked
@SuppressWarnings("unused")
public final class Resolvers {

    private static final SimpleCommandExceptionType NO_ENTITIES_FOUND = new SimpleCommandExceptionType(message().serialize(translatable("argument.entity.notfound.entity")));
    private static final SimpleCommandExceptionType NO_PLAYERS_FOUND = new SimpleCommandExceptionType(message().serialize(translatable("argument.entity.notfound.player")));

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 단일 엔티티를 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 엔티티
     */
    public static Entity entity(final CommandContext<CommandSourceStack> context, final String name) throws CommandSyntaxException {
        return context
            .getArgument(name, EntitySelectorArgumentResolver.class)
            .resolve(context.getSource())
            .getFirst();
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 엔티티 목록을 해석합니다.
     * 여러 엔티티를 반환할 수 있으며, 일치하는 엔티티가 없는 경우 빈 목록을 반환할 수 있습니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 엔티티 목록
     */
    public static List<Entity> optionalEntities(final CommandContext<CommandSourceStack> context, final String name) throws CommandSyntaxException {
        return context
            .getArgument(name, EntitySelectorArgumentResolver.class)
            .resolve(context.getSource());
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 엔티티 목록을 해석합니다.
     * 여러 엔티티를 반환할 수 있으며, 일치하는 엔티티가 없는 경우 오류 메시지를 전달합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 엔티티 목록
     */
    public static List<Entity> entities(final CommandContext<CommandSourceStack> context, final String name) throws CommandSyntaxException {
        final List<Entity> entities = Resolvers.optionalEntities(context, name);
        if (entities.isEmpty()) {
            throw NO_ENTITIES_FOUND.create();
        } else {
            return entities;
        }
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 단일 플레이어를 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 플레이어
     */
    public static Player player(final CommandContext<CommandSourceStack> context, final String name) throws CommandSyntaxException {
        return context
            .getArgument(name, PlayerSelectorArgumentResolver.class)
            .resolve(context.getSource())
            .getFirst();
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 플레이어 목록을 해석합니다.
     * 여러 플레이어를 반환할 수 있으며, 일치하는 플레이어가 없는 경우 빈 목록을 반환할 수 있습니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 플레이어 목록
     */
    public static List<Player> optionalPlayers(final CommandContext<CommandSourceStack> context, final String name) throws CommandSyntaxException {
        return context
            .getArgument(name, PlayerSelectorArgumentResolver.class)
            .resolve(context.getSource());
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 플레이어 목록을 해석합니다.
     * 여러 플레이어를 반환할 수 있으며, 일치하는 플레이어가 없는 경우 오류 메시지를 전달합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 플레이어 목록
     */
    public static List<Player> players(final CommandContext<CommandSourceStack> context, final String name) throws CommandSyntaxException {
        final List<Player> entities = Resolvers.optionalPlayers(context, name);
        if (entities.isEmpty()) {
            throw NO_PLAYERS_FOUND.create();
        } else {
            return entities;
        }
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 플레이어 프로필을 해석합니다.
     * 여러 플레이어 프로필을 반환할 수 있습니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 플레이어 프로필 목록
     */
    public static Collection<PlayerProfile> playerProfiles(final CommandContext<CommandSourceStack> context, final String name) throws CommandSyntaxException {
        return context
            .getArgument(name, PlayerProfileListResolver.class)
            .resolve(context.getSource());
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 정수형 위치를 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 정수형 위치
     */
    public static BlockPosition blockPosition(final CommandContext<CommandSourceStack> context, final String name) throws CommandSyntaxException {
        return context
            .getArgument(name, BlockPositionResolver.class)
            .resolve(context.getSource());
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 2차원 정수형 위치를 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 2차원 정수형 위치
     */
    public static ColumnBlockPosition columnBlockPosition(final CommandContext<CommandSourceStack> context, final String name) throws CommandSyntaxException {
        return context
            .getArgument(name, ColumnBlockPositionResolver.class)
            .resolve(context.getSource());
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 블록 예측을 가져옵니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 블록 예측
     */
    public static BlockInWorldPredicate blockInWorldPredicate(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, BlockInWorldPredicate.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 실수형 위치를 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 실수형 위치
     */
    public static FinePosition finePosition(final CommandContext<CommandSourceStack> context, final String name) throws CommandSyntaxException {
        return context
            .getArgument(name, FinePositionResolver.class)
            .resolve(context.getSource());
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 2차원 실수형 위치를 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 2차원 실수형 위치
     */
    public static ColumnFinePosition columnFinePosition(final CommandContext<CommandSourceStack> context, final String name) throws CommandSyntaxException {
        return context
            .getArgument(name, ColumnFinePositionResolver.class)
            .resolve(context.getSource());
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 회전을 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 회전
     */
    public static Rotation rotation(final CommandContext<CommandSourceStack> context, final String name) throws CommandSyntaxException {
        return context
            .getArgument(name, RotationResolver.class)
            .resolve(context.getSource());
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 실수형 각도를 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 실수형 각도
     */
    public static float angle(final CommandContext<CommandSourceStack> context, final String name) throws CommandSyntaxException {
        return context
            .getArgument(name, AngleResolver.class)
            .resolve(context.getSource());
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 축 세트를 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 축 세트
     */
    public static AxisSet axes(final CommandContext<CommandSourceStack> context, final String name) throws CommandSyntaxException {
        return context.getArgument(name, AxisSet.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 블록 상태를 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 블록 상태
     */
    public static BlockState blockState(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, BlockState.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 아이템 스택을 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 아이템 스택
     */
    public static ItemStack itemStack(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, ItemStack.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 아이템 예측을 가져옵니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 아이템 예측
     */
    public static ItemStackPredicate itemPredicate(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, ItemStackPredicate.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 지정된 색을 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 지정된 색
     */
    public static NamedTextColor namedColor(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, NamedTextColor.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 십육진 색을 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 십육진 색
     */
    public static TextColor hexColor(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, TextColor.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 컴포넌트를 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 컴포넌트
     */
    public static Component component(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, Component.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 스타일을 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 스타일
     */
    public static Style style(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, Style.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 서명된 메시지를 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 서명된 메시지
     */
    public static SignedMessageResolver signedMessage(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, SignedMessageResolver.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 스코어보드 슬롯을 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 스코어보드 슬롯
     */
    public static DisplaySlot scoreboardDisplaySlot(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, DisplaySlot.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 키를 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 키
     */
    public static NamespacedKey namespacedKey(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, NamespacedKey.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 키를 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 키
     */
    public static Key key(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, Key.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 정수 범위를 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 정수 범위
     */
    public static Range<Integer> integerRange(final CommandContext<CommandSourceStack> context, final String name) {
        return context
            .getArgument(name, IntegerRangeProvider.class)
            .range();
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 실수 범위를 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 실수 범위
     */
    public static Range<Double> doubleRange(final CommandContext<CommandSourceStack> context, final String name) {
        return context
            .getArgument(name, DoubleRangeProvider.class)
            .range();
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 월드를 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 월드
     */
    public static World world(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, World.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 게임모드를 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 게임모드
     */
    public static GameMode gameMode(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, GameMode.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 하이트 맵을 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 하이트 맵
     */
    public static HeightMap heightMap(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, HeightMap.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 UUID를 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return UUID
     */
    public static UUID uuid(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, UUID.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 목적 기준을 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 목적 기준
     */
    public static Criteria objectiveCriteria(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, Criteria.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 주목 지점을 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 주목 지점
     */
    public static LookAnchor entityAnchor(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, LookAnchor.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 시간을 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 시간 (틱)
     */
    public static int time(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, Integer.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 구조물 반전 타입을 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 구조물 반전 타입
     */
    public static Mirror templateMirror(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, Mirror.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 구조물 회전 타입을 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 구조물 회전 타입
     */
    public static StructureRotation templateRotation(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, StructureRotation.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 레지스트리 리소스를 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 레지스트리 리소스
     * @param <T> 값 타입
     */
    @SuppressWarnings("unchecked")
    public static <T> T resource(final CommandContext<CommandSourceStack> context, final String name) {
        return (T) context.getArgument(name, Object.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 레지스트리의 타입 키를 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 레지스트리 타입 키
     * @param <T> 값 타입
     */
    @SuppressWarnings("unchecked")
    public static <T> TypedKey<T> resourceKey(final CommandContext<CommandSourceStack> context, final String name) {
        return (TypedKey<T>) context.getArgument(name, TypedKey.class);
    }

    // Daydream start - Expose more brigadier argument types
    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 플레이어 프로필을 해석합니다.
     * 여러 플레이어 프로필을 반환할 수 있습니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 플레이어 프로필 목록
     */
    @SuppressWarnings("unchecked")
    public static Collection<GameProfile> gameProfiles(final CommandContext<CommandSourceStack> context, final String name) {
        return (Collection<GameProfile>) context.getArgument(name, Collection.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 컴파운드 태그를 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 컴파운드 태그
     */
    public static BinaryTagHolder compoundTag(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, BinaryTagHolder.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 슬롯을 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 슬롯 번호
     */
    public static int slot(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, Integer.class);
    }

    /**
     * 명령어 컨텍스트와 주어진 인수 이름으로부터 슬롯 목록을 해석합니다.
     *
     * @param context 명령어 컨텍스트
     * @param name 인수의 이름
     * @return 슬롯 번호 목록
     */
    public static IntList slots(final CommandContext<CommandSourceStack> context, final String name) {
        return context.getArgument(name, IntList.class);
    }
    // Daydream end - Expose more brigadier argument types

    private Resolvers() {
    }
}
