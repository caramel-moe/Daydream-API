package moe.caramel.daydream.event.configuration;

import io.papermc.paper.connection.PlayerConfigurationConnection;
import org.bukkit.event.Event;
import org.jetbrains.annotations.ApiStatus;

/**
 * 구성 단계에서 호출되는 이벤트
 */
public abstract class ConfigurationEvent extends Event {

    protected final PlayerConfigurationConnection connection;

    @ApiStatus.Internal
    public ConfigurationEvent(final PlayerConfigurationConnection connection, final boolean async) {
        super(async);
        this.connection = connection;
    }

    /**
     * 접속 중인 구성 연결을 가져옵니다.
     *
     * @return 구성 연결
     */
    public PlayerConfigurationConnection getConnection() {
        return connection;
    }
}
