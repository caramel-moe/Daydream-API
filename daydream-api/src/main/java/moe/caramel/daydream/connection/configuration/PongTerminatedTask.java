package moe.caramel.daydream.connection.configuration;

/**
 * 클라이언트로부터 Pong 패킷을 전송받았을 때 종료하는 작업
 */
public non-sealed interface PongTerminatedTask extends ConfigurationTask {

    /**
     * Pong 검증에 사용할 정수 아이디를 가져옵니다.
     * <p>
     * 이 값은 변경될 수 없습니다.
     *
     * @return Pong 검증에 사용할 정수 아이디
     */
    int getPingId();
}
