package moe.caramel.daydream.event.configuration;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * Configuration 페이즈에서 호출되는 이벤트
 */
@NullMarked
public abstract class ConfigurationEvent extends PlayerEvent {

    @ApiStatus.Internal
    public ConfigurationEvent(final Player who) {
        super(who, !Bukkit.isPrimaryThread()); // (...)
    }
}
