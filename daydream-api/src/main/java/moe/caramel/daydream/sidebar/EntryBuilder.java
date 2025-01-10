package moe.caramel.daydream.sidebar;

import io.papermc.paper.scoreboard.numbers.NumberFormat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * SideBar API 사용을 위한 {@link Entry} 빌더
 */
@NullMarked
public final class EntryBuilder {

    public static final int MAX_ENTRY_SIZE = 15;
    private final List<Entry> entries = new ArrayList<>(MAX_ENTRY_SIZE);

    private EntryBuilder() {
    }

    /**
     * Entry 빌더를 생성합니다.
     *
     * @return {@link EntryBuilder}
     */
    public static EntryBuilder builder() {
        return new EntryBuilder();
    }

    /**
     * 현재 {@link Entry} 리스트의 크기를 가져옵니다.
     *
     * @return 현재 {@link Entry} 리스트의 크기
     */
    public int size() {
        return entries.size();
    }

    /**
     * {@link Entry} 리스트에 빈 줄을 추가합니다.
     *
     * @return this
     */
    public EntryBuilder blank() {
        return blank(null);
    }

    /**
     * {@link Entry} 리스트에 빈 줄을 추가합니다.
     *
     * @param format 점수 표기 방식
     * @return this
     */
    public EntryBuilder blank(final @Nullable NumberFormat format) {
        return next(Component.empty(), format);
    }

    /**
     * {@link Entry} 리스트에 제공한 텍스트를 추가합니다.
     *
     * @param value 리스트에 추가할 텍스트
     * @return this
     */
    public EntryBuilder next(final String value) {
        return next(value, null);
    }

    /**
     * {@link Entry} 리스트에 제공한 텍스트를 추가합니다.
     *
     * @param value  리스트에 추가할 텍스트
     * @param format 점수 표기 방식
     * @return this
     */
    public EntryBuilder next(final String value, final @Nullable NumberFormat format) {
        return next(MiniMessage.miniMessage().deserialize(value), format);
    }

    /**
     * {@link Entry} 리스트에 제공한 텍스트를 추가합니다.
     *
     * @param value 리스트에 추가할 텍스트
     * @return this
     */
    public EntryBuilder next(final Component value) {
        return next(value, null);
    }

    /**
     * {@link Entry} 리스트에 제공한 텍스트를 추가합니다.
     *
     * @param value  리스트에 추가할 텍스트
     * @param format 점수 표기 방식
     * @return this
     */
    public EntryBuilder next(final Component value, final @Nullable NumberFormat format) {
        if (entries.size() < MAX_ENTRY_SIZE) {
            this.entries.add(new Entry(value, format));
        }
        return this;
    }

    /**
     * 사이드바에 표시될 항목의 데이터
     *
     * @param value 표시 값
     * @param numberFormat 숫자 포맷
     */
    public record Entry(Component value, @Nullable NumberFormat numberFormat) {
    }

    @ApiStatus.Internal
    public List<Entry> entries() {
        return entries;
    }
}
