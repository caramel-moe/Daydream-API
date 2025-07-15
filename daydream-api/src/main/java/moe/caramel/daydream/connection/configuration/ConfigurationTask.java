package moe.caramel.daydream.connection.configuration;

import io.papermc.paper.connection.PlayerConfigurationConnection;

/**
 * 구성 단계에서 실행되는 사용자 지정 작업
 *
 * @see PlayerConfigurationConnection#finishCurrentTask()
 */
public sealed interface ConfigurationTask permits ManuallyTerminatedTask, PongTerminatedTask {

    /**
     * 작업이 시작될 때 호출됩니다.
     *
     * @param connection 플레이어 연결
     */
    void start(PlayerConfigurationConnection connection);

    /**
     * 작업이 종료될 때 호출됩니다.
     *
     * @param connection 플레이어 연결
     */
    default void terminate(final PlayerConfigurationConnection connection) {
    }
}
