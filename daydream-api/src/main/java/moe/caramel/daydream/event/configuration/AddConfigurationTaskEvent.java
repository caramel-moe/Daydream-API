package moe.caramel.daydream.event.configuration;

import io.papermc.paper.connection.PlayerConfigurationConnection;
import moe.caramel.daydream.connection.configuration.ConfigurationTask;
import net.kyori.adventure.key.Key;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;

/**
 * 플레이어가 구성 단계에 진입 후, 작업을 추가할 때 호출되는 이벤트
 * <p>
 * 레지스트리 전송 이전에 호출됩니다.
 */
public final class AddConfigurationTaskEvent extends ConfigurationEvent {

    private final TaskRegistry taskRegistry;

    @ApiStatus.Internal
    public AddConfigurationTaskEvent(final PlayerConfigurationConnection connection, final TaskRegistry registry) {
        super(connection, !Bukkit.isPrimaryThread());
        this.taskRegistry = registry;
    }

    /**
     * 테스크를 등록합니다.
     *
     * @param key 테스크의 키
     * @param task 실행할 작업
     * @throws IllegalArgumentException 이미 등록된 테스크를 등록하는 경우 던져집니다.
     */
    public void registerTask(final Key key, final ConfigurationTask task) {
        this.taskRegistry.register(key, task);
    }

    @ApiStatus.NonExtendable
    public interface TaskRegistry {

        void register(Key key, ConfigurationTask task);
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
