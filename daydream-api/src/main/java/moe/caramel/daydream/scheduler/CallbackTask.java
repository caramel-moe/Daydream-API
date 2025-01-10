package moe.caramel.daydream.scheduler;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import java.util.function.Consumer;

/**
 * Folia 스케줄러에서 스케줄 취소 요청을 받았을 때 수행할 작업을 지정할 수 있습니다.
 *
 * @see io.papermc.paper.threadedregions.scheduler.AsyncScheduler
 * @see io.papermc.paper.threadedregions.scheduler.EntityScheduler
 * @see io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler
 * @see io.papermc.paper.threadedregions.scheduler.RegionScheduler
 */
public interface CallbackTask extends Consumer<ScheduledTask> {

    /**
     * 스케줄 취소 요청을 받았을 때 수행할 작업
     */
    void cancelCallback();
}
