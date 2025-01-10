package moe.caramel.daydream.player;

import org.bukkit.Bukkit;
import org.jspecify.annotations.NullMarked;
import java.security.PublicKey;
import java.time.Duration;
import java.time.Instant;

/**
 * 플레이어 식별키
 */
@NullMarked
public interface IdentifyKey {

    /**
     * 플레이어 식별키를 생성합니다.
     *
     * @param expiresAt 공개키의 만료 시간
     * @param publicKey 플레이어의 공개키
     * @param signature 공개키의 서명
     * @return 플레이어 식별키
     */
    @SuppressWarnings("deprecation")
    static IdentifyKey create(final Instant expiresAt, final PublicKey publicKey, final byte[] signature) {
        return Bukkit.getUnsafe().createIdentifyKey(expiresAt, publicKey, signature);
    }

    /**
     * 공개키의 만료 시간을 가져옵니다.
     *
     * @return 만료 시간
     */
    Instant profileExpiresAt();

    /**
     * 플레이어의 공개키를 가져옵니다.
     *
     * @return 플레이어의 공개키
     */
    PublicKey publicKey();

    /**
     * 공개키의 서명을 가져옵니다.
     *
     * @return 공개키의 서명
     */
    byte[] signature();

    /**
     * 키 만료 여부를 확인합니다.
     *
     * @return 만료되었다면 {@code true}를 반환
     */
    boolean isExpired();

    /**
     * 키 만료 여부를 확인합니다.
     *
     * @param gracePeriod 유예 기간
     * @return 만료되었다면 {@code true}를 반환
     */
    boolean isExpired(Duration gracePeriod);
}
