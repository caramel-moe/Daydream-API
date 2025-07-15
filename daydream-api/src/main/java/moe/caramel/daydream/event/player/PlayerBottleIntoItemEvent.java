package moe.caramel.daydream.event.player;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.Nullable;

/**
 * 플레이어가 병에 무언가를 담았을 때 호출됩니다.
 */
public final class PlayerBottleIntoItemEvent extends PlayerEvent implements Cancellable {

    private final ItemType type;
    private final @Nullable Block clickedBlock;
    private final @Nullable Entity clickedEntity;
    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerBottleIntoItemEvent(
        final Player player,
        final ItemType type,
        final @Nullable Block clickedBlock,
        final @Nullable Entity clickedEntity
    ) {
        super(player);
        this.type = type;
        this.clickedBlock = clickedBlock;
        this.clickedEntity = clickedEntity;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    /**
     * 병에 담길 아이템의 타입을 가져옵니다.
     *
     * @return 병에 담길 아이템의 타입
     */
    public ItemType getType() {
        return type;
    }

    /**
     * 플레이어가 상호 작용한 블록을 가져옵니다.
     * <br>
     * {@link ItemType#WATER}인 경우에만 null이 아닙니다.
     *
     * @return 플레이어가 상호 작용한 블록
     */
    public @Nullable Block getClickedBlock() {
        return clickedBlock;
    }

    /**
     * 플레이어가 상호 작용한 엔티티를 가져옵니다.
     * <br>
     * {@link ItemType#DRAGON_BREATH}인 경우에만 null이 아닙니다.
     *
     * @return 플레이어가 상호 작용한 엔티티
     */
    public @Nullable Entity getClickedEntity() {
        return clickedEntity;
    }

    /**
     * 병에 담길 아이템의 타입
     */
    public enum ItemType {
        /**
         * 드래곤의 숨결
         */
        DRAGON_BREATH,
        /**
         * 물
         */
        WATER
    }

    //<editor-fold desc="Bukkit Event Handler (Internal)" defaultstate="collapsed">
    private static final HandlerList HANDLER_LIST = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
    //</editor-fold>
}
