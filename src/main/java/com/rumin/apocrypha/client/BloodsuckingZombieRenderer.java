package com.rumin.apocrypha.client;

import com.rumin.apocrypha.Apocrypha;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.Identifier;

public class BloodsuckingZombieRenderer extends ZombieRenderer {
    public BloodsuckingZombieRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public static final Identifier TEXTURE=
            Identifier.fromNamespaceAndPath(Apocrypha.MOD_ID,"textures/entity/bloodsucking_zombie.png");

    @Override
    public Identifier getTextureLocation(ZombieRenderState state){
        return TEXTURE;
    }
}
