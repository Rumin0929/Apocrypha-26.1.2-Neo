package com.rumin.apocrypha.util;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.RandomizableContainer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class LookedAtContainerFinder {
    private LookedAtContainerFinder() {
    }

    public static RandomizableContainer findLoot(ServerPlayer serverPlayer){
        ServerLevel serverLevel = (ServerLevel) serverPlayer.level();
        ChunkPos chunkPosition = serverPlayer.chunkPosition();
        List<BlockEntity> nearbyBlockEntities = new ArrayList<>();
        for (int chunkOffsetX = -1; chunkOffsetX <= 1; chunkOffsetX++) {
            for (int chunkOffsetZ = -1; chunkOffsetZ <= 1; chunkOffsetZ++) {
                nearbyBlockEntities.addAll(
                        serverLevel.getChunk(chunkPosition.x() + chunkOffsetX, chunkPosition.z() + chunkOffsetZ)
                                .getBlockEntities()
                                .values()
                );
            }
        }

        Vec3 lookDirection = serverPlayer.getLookAngle();
        Vec3 eyePosition = serverPlayer.getEyePosition();
        RandomizableContainer targetContainer = null;
        double bestDotProduct = 0.9;
        for (BlockEntity blockEntity : nearbyBlockEntities) {
            if (!(blockEntity instanceof RandomizableContainer randomizableContainer)) {
                continue;
            }

            Vec3 directionToContainer = Vec3.atCenterOf(randomizableContainer.getBlockPos())
                    .subtract(eyePosition)
                    .normalize();

            double dotProduct = lookDirection.dot(directionToContainer);
            if (dotProduct > bestDotProduct) {
                bestDotProduct = dotProduct;
                targetContainer = randomizableContainer;
            }
        }

        return targetContainer;
    }
}
