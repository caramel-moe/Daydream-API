package moe.caramel.daydream.sidebar;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jspecify.annotations.NullMarked;
import java.util.Set;
import java.util.UUID;

@NullMarked
public interface SidebarProcessor {

    /**
     * 특정 플레이어에게 개인 사이드바 기능을 활성화합니다.
     *
     * @param owner 소유 플러그인
     * @param decorator 사용자 지정 {@link SidebarDecorator} 데코레이터
     * @param holder 대상 플레이어
     * @param updateInterval 사이드바 업데이트 시간(Tick), 0 이하일 수 없습니다.
     * @return 이미 사용하고 있는 경우 {@code false}를 반환
     */
    boolean activate(Plugin owner, SidebarDecorator decorator, Player holder, long updateInterval);

    /**
     * 특정 플레이어에게 개인 사이드바 기능을 비활성화합니다.
     *
     * @param holder 대상 플레이어
     * @return 활성화되어있지 않은 경우 {@code false}를 반환
     */
    default boolean deactivate(final Player holder) {
        return deactivate(holder.getUniqueId());
    }

    /**
     * 특정 플레이어에게 개인 사이드바 기능을 비활성화합니다.
     *
     * @param uuid 대상 플레이어의 UUID
     * @return 활성화되어있지 않은 경우 {@code false}를 반환
     */
    boolean deactivate(UUID uuid);

    /**
     * 특정 플레이어의 개인 사이드바 기능 사용 여부를 가져옵니다.
     *
     * @param holder 대상 플레이어
     * @return 개인 사이드바 기능 사용 여부
     */
    default boolean activated(final Player holder) {
        return activated(holder.getUniqueId());
    }

    /**
     * 특정 플레이어의 개인 사이드바 기능 사용 여부를 가져옵니다.
     *
     * @param uuid 대상 플레이어의 UUID
     * @return 개인 사이드바 기능 사용 여부
     */
    boolean activated(UUID uuid);

    /**
     * 특정 플레이어의 개인 사이드바 업데이트 시간을 가져옵니다.
     *
     * @param holder 대상 플레이어
     * @return 개인 사이드바 업데이트 틱 주기 ({@code -1}인 경우 비활성화 상태)
     */
    default long updateInterval(final Player holder) {
        return updateInterval(holder.getUniqueId());
    }

    /**
     * 특정 플레이어의 개인 사이드바 업데이트 시간을 가져옵니다.
     *
     * @param uuid 대상 플레이어의 UUID
     * @return 개인 사이드바 업데이트 틱 주기 ({@code -1}인 경우 비활성화 상태)
     */
    long updateInterval(UUID uuid);

    /**
     * 특정 플레이어의 개인 사이드바 업데이트 시간을 설정합니다.
     *
     * @param holder 대상 플레이어
     * @param updateInterval 개인 사이드바 업데이트 틱 주기
     * @return {@code true}인 경우 변경에 성공
     * @throws IllegalArgumentException 업데이트 주기가 0틱 보다 작을 경우 예외를 던짐
     */
    default boolean updateInterval(final Player holder, final long updateInterval) {
        return updateInterval(holder.getUniqueId(), updateInterval);
    }

    /**
     * 특정 플레이어의 개인 사이드바 업데이트 시간을 설정합니다.
     *
     * @param uuid 대상 플레이어의 UUID
     * @param updateInterval 개인 사이드바 업데이트 틱 주기
     * @return {@code true}인 경우 변경에 성공
     * @throws IllegalArgumentException 업데이트 주기가 0틱 보다 작을 경우 예외를 던짐
     */
    boolean updateInterval(UUID uuid, long updateInterval);

    /**
     * 개인 사이드바가 활성화된 플레이어의 UUID 목록을 가져옵니다.
     *
     * @return UUID 목록
     */
    Set<UUID> trackedPlayers();
}
