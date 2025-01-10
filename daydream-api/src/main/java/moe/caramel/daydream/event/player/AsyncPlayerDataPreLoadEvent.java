package moe.caramel.daydream.event.player;

import com.mojang.authlib.GameProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import java.net.InetAddress;
import java.util.UUID;

/**
 * 비동기 플레이어 데이터 사전 로드 이벤트
 * <p>
 * {@link org.bukkit.event.player.AsyncPlayerPreLoginEvent} 호출 이후에
 * 플레이어의 {@link GameProfile}이 완성되면 호출됩니다.
 */
@NullMarked
public abstract class AsyncPlayerDataPreLoadEvent extends Event {

    private @Nullable Component disconnectMessage;

    @ApiStatus.Internal
    public AsyncPlayerDataPreLoadEvent() {
        super(true);
    }

    @Override
    public final boolean isSupportInterrupt() {
        return true;
    }

    /**
     * 플레이어의 원본 프로필을 가져옵니다.
     *
     * @return 플레이어의 원본 프로필
     */
    public abstract GameProfile getOriginalProfile();

    /**
     * 플레이어의 프로필을 가져옵니다.
     *
     * @return 플레이어의 프로필
     */
    public abstract GameProfile getProfile();

    /**
     * 플레이어의 이름을 가져옵니다.
     *
     * @return 플레이어의 이름
     */
    public final String getName() {
        return getOriginalProfile().getName();
    }

    /**
     * 플레이어의 UUID를 가져옵니다.
     *
     * @return 플레이어의 UUID
     */
    public final UUID getUniqueId() {
        return getOriginalProfile().getId();
    }

    /**
     * 플레이어의 IP 주소를 가져옵니다.
     *
     * @return 플레이어의 IP 주소
     */
    public abstract InetAddress getAddress();

    /**
     * 연결 해제 메시지를 가져옵니다. 메시지가 설정되지 않은 경우
     * 플레이어의 연결을 해제하지 않습니다.
     *
     * @return 연결 해제 메시지
     */
    public final @Nullable Component getDisconnectMessage() {
        return disconnectMessage;
    }

    /**
     * 연결 해제 메시지를 설정합니다. 메시지가 설정되지 않은 경우
     * 플레이어의 연결을 해제하지 않습니다.
     *
     * @param disconnectMessage 연결 해제 메시지
     */
    public final void setDisconnectMessage(final @Nullable Component disconnectMessage) {
        this.disconnectMessage = disconnectMessage;
    }

    // Daydream start - (feature) Add more Advancement API
    /**
     * 플레이어 발전 과제 데이터를 생성하고 가져옵니다.
     * <p>
     * 플레이어의 로그인이 완료되기 전에 생성되기에 중복 로그인으로
     * 인한 데이터 증발 등 데이터 관리에 주의해주세요.
     * </p>
     *
     * @return 플레이어 발전 과제 데이터 (이미 생성된 경우 기존의 인스턴스를 가져옴)
     */
    public abstract moe.caramel.daydream.advancement.PlayerAdvancementData getOrCreateAdvancements();
    // Daydream end - (feature) Add more Advancement API

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
