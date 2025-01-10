package moe.caramel.daydream.event.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * 포션 이펙트를 Tick 할 때 호출됩니다.
 */
@NullMarked
public final class EntityPotionTickEvent extends EntityEvent implements Cancellable {

    private final PotionEffectType type;
    private final int amplifier;
    private boolean cancelled;

    @ApiStatus.Internal
    public EntityPotionTickEvent(final LivingEntity what, final PotionEffectType type, final int amplifier) {
        super(what);
        this.type = type;
        this.amplifier = amplifier;
    }

    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) entity;
    }

    /**
     * 포션 이펙트의 타입을 가져옵니다.
     *
     * @return 포션 이펙트의 타입
     */
    public PotionEffectType getType() {
        return type;
    }

    /**
     * 포션 이펙트의 강도를 가져옵니다.
     *
     * @return 포션 이펙트의 강도
     */
    public int getAmplifier() {
        return amplifier;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
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
