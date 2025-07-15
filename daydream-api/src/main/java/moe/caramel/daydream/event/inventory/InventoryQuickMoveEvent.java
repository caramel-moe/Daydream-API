package moe.caramel.daydream.event.inventory;

import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;

/**
 * 플레이어가 인벤토리를 클릭하여 {@link org.bukkit.event.inventory.InventoryAction#MOVE_TO_OTHER_INVENTORY}의
 * 처리가 시작되었을 때 호출됩니다. 이벤트 호출 결과가 기본 값인 {@link Result#ALLOW}인 경우
 * 기존 로직을 그대로 수행하며, {@link Result#DEFAULT}인 경우 해당 슬롯의 처리를 건너뜁니다.
 * {@link Result#DENY}의 경우 해당 슬롯을 포함하여 다음 슬롯의 처리까지 모두 취소합니다.
 */
public final class InventoryQuickMoveEvent extends InventoryInteractEvent {

    private final ItemStack from;
    private final ItemStack to;
    private final boolean checkEmpty;
    private int slot;

    @ApiStatus.Internal
    public InventoryQuickMoveEvent(final InventoryView view, final ItemStack from, final ItemStack to, final int slot, final boolean checkEmpty) {
        super(view);
        this.setResult(Result.ALLOW);
        this.from = from;
        this.to = to;
        this.slot = slot;
        this.checkEmpty = checkEmpty;
    }

    /**
     * 빠른 아이템 이동의 대상 아이템을 가져옵니다.
     *
     * @return 이동 대상 아이템
     */
    public ItemStack getFrom() {
        return from;
    }

    /**
     * 병합 검사 중인 아이템을 가져옵니다.
     *
     * @return 병합 검사 중인 아이템
     */
    public ItemStack getTo() {
        return to;
    }

    /**
     * 병합 위치 슬롯을 가져옵니다.
     *
     * @return 병합 위치 슬롯
     */
    public int getSlot() {
        return slot;
    }

    /**
     * 병합 슬롯 위치를 변경합니다.
     *
     * @apiNote {@link #isCheckEmpty()}가 {@code false}인
     * 경우에는 작동하지 않습니다.
     * @param slot 병합 슬롯 위치
     */
    public void setSlot(final int slot) {
        this.slot = slot;
    }

    /**
     * 빈 슬롯 확인 여부를 가져옵니다.
     *
     * @return {@code true}인 경우, 현재 빈 슬롯을 확인합니다.
     */
    public boolean isCheckEmpty() {
        return checkEmpty;
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
