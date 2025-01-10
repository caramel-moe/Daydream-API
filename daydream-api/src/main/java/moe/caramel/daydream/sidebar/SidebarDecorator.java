package moe.caramel.daydream.sidebar;

import io.papermc.paper.scoreboard.numbers.NumberFormat;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 사이드바 데코레이터
 */
@NullMarked
public interface SidebarDecorator {

    /**
     * 사이드바 글로벌 포맷을 가져옵니다.
     *
     * @param player 대상 플레이어
     * @return 사이드바 글로벌 포맷
     */
    default @Nullable NumberFormat globalFormat(final Player player) {
        return null;
    }

    /**
     * 플레이어별 사이드바에 표시할 제목을 가져옵니다.
     *
     * @param context 사이드바 스케줄러 컨텍스트
     * @param player 대상 플레이어
     * @return 사이드바에 표시할 제목
     */
    Component title(SidebarRunnable context, Player player);

    /**
     * 플레이어별 사이드바에 표시할 항목 리스트를 가져옵니다.
     * <p>
     * {@code null}을 반환하는 경우 리스트를 업데이트하지 않습니다.
     *
     * @param context 사이드바 스케줄러 컨텍스트
     * @param player 대상 플레이어
     * @return 사이드바에 표시할 항목 리스트
     */
    @Nullable EntryBuilder entries(SidebarRunnable context, Player player);
}
