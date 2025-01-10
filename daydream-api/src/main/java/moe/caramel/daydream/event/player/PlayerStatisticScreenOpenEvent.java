package moe.caramel.daydream.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * 플레이어가 통계 화면을 열기 위해 서버에게 통계 데이터를 요청한 경우 호출됩니다.
 */
@NullMarked
public final class PlayerStatisticScreenOpenEvent extends PlayerEvent implements Cancellable {

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerStatisticScreenOpenEvent(final Player player) {
        super(player);
    }

    /**
     * 통계 데이터를 클라이언트에게 전송할지의 여부를 가져옵니다.
     *
     * @return 통계 데이터를 전송할지의 여부
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * 통계 데이터를 클라이언트에게 전송할지 여부를 설정합니다.
     * <p>
     * <b>주의:</b> 이벤트를 취소해도 클라이언트에는 "통계 로드 중..." 화면이 계속 출력됩니다.
     * 인벤토리를 열고 닫는 방법을 사용하여 로드 화면을 제거할 수 있습니다.
     *
     * @param cancel {@code true}인 경우 통계 데이터를 클라이언트에게 전송하지 않습니다.
     */
    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
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
