package moe.caramel.daydream.sidebar;

import org.jspecify.annotations.NullMarked;

/**
 * 사이드바 스케줄러 컨텍스트
 */
@NullMarked
public interface SidebarRunnable {

    /**
     * 개인 사이드바 업데이트 시간을 가져옵니다.
     *
     * @return 개인 사이드바 업데이트 틱 주기
     */
    long updateInterval();

    /**
     * 개인 사이드바 업데이트 시간을 설정합니다.
     *
     * @param updateInterval 개인 사이드바 업데이트 틱 주기
     * @throws IllegalArgumentException 업데이트 주기가 0틱 보다 작을 경우 예외를 던짐
     */
    void updateInterval(long updateInterval);
}
