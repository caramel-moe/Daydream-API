package moe.caramel.daydream.player.chat;

import net.kyori.adventure.chat.SignedMessage;
import net.kyori.adventure.text.Component;
import org.jspecify.annotations.NullMarked;
import java.time.Instant;
import java.util.UUID;

/**
 * 플레이어 메시지
 */
@NullMarked
public interface PlayerMessage extends SignedMessage {

    /**
     * 메시지를 발신한 대상의 UUID를 가져옵니다.
     *
     * @return 메시지를 발신한 대상의 UUID
     */
    UUID sender();

    /**
     * 메시지가 서버 시간 기준으로 만료되었는지 확인합니다.
     *
     * @param currentTime 현재 시간
     * @return 만료되었다면 {@code true}를 반환
     */
    boolean hasExpiredServer(Instant currentTime);

    /**
     * 메시지가 클라이언트 시간 기준으로 만료되었는지 확인합니다.
     *
     * @param currentTime 현재 시간
     * @return 만료되었다면 {@code true}를 반환
     */
    boolean hasExpiredClient(Instant currentTime);

    /**
     * 메시지가 서명을 가지고 있는지의 여부를 가져옵니다.
     *
     * @return 메시지가 서명을 가지고 있는지의 여부
     */
    boolean hasSignature();

    /**
     * 특정 대상의 서명인지 확인합니다.
     *
     * @param uuid 대상
     * @return 대상의 서명이 맞다면 {@code true}를 반환
     */
    boolean hasSignatureFrom(UUID uuid);

    /**
     * 꾸며진 메시지를 가져옵니다.
     *
     * @return 꾸며진 메시지가 없는 경우 원본을 반환
     */
    Component decoratedContent();

    /**
     * 서명되지 않은 메시지를 설정합니다. 주로 메시지를 꾸미는 용도로 사용합니다.
     *
     * @param message 서명되지 않은 메시지
     * @return 새 메시지 인스턴스
     */
    PlayerMessage unsignedContent(Component message);

    /**
     * 서명되지 않은 메시지를 제거합니다. 받는 대상은 원본 메시지로 표시됩니다.
     *
     * @return 새 메시지 인스턴스
     */
    PlayerMessage removeUnsignedContent();

    /**
     * 메시지가 모두 필터링 되었는지의 여부를 가져옵니다.
     *
     * @return 메시지가 모두 필터링 되었는지의 여부
     */
    boolean isFullyFiltered();
}
