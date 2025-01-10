package moe.caramel.daydream.player;

import net.kyori.adventure.resource.ResourcePackInfo;
import net.kyori.adventure.resource.ResourcePackStatus;
import org.bukkit.event.player.PlayerResourcePackStatusEvent.Status;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 리소스팩 데이터
 */
@NullMarked
public interface ResourcePack extends ResourcePackInfo {

    /**
     * 리소스팩 적용 상태를 Adventure 형식으로 가져옵니다.
     *
     * @return 리소스팩 적용 상태
     */
    @Nullable ResourcePackStatus status();

    /**
     * 리소스팩 적용 상태를 Bukkit 형식으로 가져옵니다.
     *
     * @return 리소스팩 적용 상태
     */
    default @Nullable Status bukkitStatus() {
        return adventureToBukkit(status());
    }

    //<editor-fold desc="Status Converter" defaultstate="collapsed">
    /**
     * Adventure 리소스팩 상태를 Bukkit 형식으로 변환합니다.
     *
     * @param status Adventure 상태
     * @return Bukkit 상태
     */
    @Contract("!null -> !null; null -> null")
    static @Nullable Status adventureToBukkit(final @Nullable ResourcePackStatus status) {
        return (status == null) ? null : switch (status) {
            case SUCCESSFULLY_LOADED -> Status.SUCCESSFULLY_LOADED;
            case DECLINED -> Status.DECLINED;
            case FAILED_DOWNLOAD -> Status.FAILED_DOWNLOAD;
            case ACCEPTED -> Status.ACCEPTED;
            case DOWNLOADED -> Status.DOWNLOADED;
            case INVALID_URL -> Status.INVALID_URL;
            case FAILED_RELOAD -> Status.FAILED_RELOAD;
            case DISCARDED -> Status.DISCARDED;
        };
    }

    /**
     *  Bukkit 리소스팩 상태를 Adventure 형식으로 변환합니다.
     *
     * @param status Bukkit 상태
     * @return Adventure 상태
     */
    @Contract("!null -> !null; null -> null")
    static @Nullable ResourcePackStatus bukkitToAdventure(final @Nullable Status status) {
        return (status == null) ? null : switch (status) {
            case SUCCESSFULLY_LOADED -> ResourcePackStatus.SUCCESSFULLY_LOADED;
            case DECLINED -> ResourcePackStatus.DECLINED;
            case FAILED_DOWNLOAD -> ResourcePackStatus.FAILED_DOWNLOAD;
            case ACCEPTED -> ResourcePackStatus.ACCEPTED;
            case DOWNLOADED -> ResourcePackStatus.DOWNLOADED;
            case INVALID_URL -> ResourcePackStatus.INVALID_URL;
            case FAILED_RELOAD -> ResourcePackStatus.FAILED_RELOAD;
            case DISCARDED -> ResourcePackStatus.DISCARDED;
        };
    }
    //</editor-fold>
}
