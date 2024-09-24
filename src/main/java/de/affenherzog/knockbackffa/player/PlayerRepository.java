package de.affenherzog.knockbackffa.player;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.NotNull;

public interface PlayerRepository {

  CompletableFuture<Void> update(@NotNull KffaPlayer kffaPlayer);

  CompletableFuture<Void> insert(@NotNull KffaPlayer kffaPlayer);

  CompletableFuture<Optional<PlayerStats>> findByUUID(@NotNull UUID uuid);
}
