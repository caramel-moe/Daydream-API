package moe.caramel.daydream.event.server;

import net.kyori.adventure.text.Component;
import org.bukkit.event.HandlerList;
import org.bukkit.event.server.ServerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 서버가 종료될 때 호출됩니다.
 */
@NullMarked
public final class ServerShutdownEvent extends ServerEvent {

    private final boolean restarting;
    private @Nullable Component overrideReason;

    @ApiStatus.Internal
    public ServerShutdownEvent(final boolean restarting) {
        this.restarting = restarting;
    }

    /**
     * 서버 재시작 여부를 가져옵니다.
     *
     * @return 서버 재시작 여부
     */
    public boolean isRestarting() {
        return restarting;
    }

    /**
     * 덮어 씌울 서버 종료 이유를 가져옵니다.
     *
     * @return 서버 종료 이유
     */
    public @Nullable Component getOverrideReason() {
        return overrideReason;
    }

    /**
     * 덮어 씌울 서버 종료 이유를 설정합니다.
     *
     * @param overrideReason 서버 종료 이유
     */
    public void setOverrideReason(final @Nullable Component overrideReason) {
        this.overrideReason = overrideReason;
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
