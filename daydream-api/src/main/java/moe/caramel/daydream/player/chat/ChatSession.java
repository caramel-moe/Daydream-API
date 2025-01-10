package moe.caramel.daydream.player.chat;

import moe.caramel.daydream.player.IdentifyKey;
import org.bukkit.Bukkit;
import org.jspecify.annotations.NullMarked;
import java.util.UUID;

/**
 * 플레이어 채팅 세션
 */
@NullMarked
public interface ChatSession {

    /**
     * 채팅 세션을 생성합니다.
     *
     * @param uniqueId 세션 고유 ID
     * @param identifyKey 프로필의 식별키
     * @return 채팅 세션
     */
    @SuppressWarnings("deprecation")
    static ChatSession create(final UUID uniqueId, final IdentifyKey identifyKey) {
        return Bukkit.getUnsafe().createChatSession(uniqueId, identifyKey);
    }

    /**
     * 세션의 고유 ID를 가져옵니다.
     *
     * @return 세션의 고유 ID
     */
    UUID uniqueId();

    /**
     * 프로필의 식별키를 가져옵니다.
     *
     * @return 프로필의 식별키
     */
    IdentifyKey identifyKey();
}
