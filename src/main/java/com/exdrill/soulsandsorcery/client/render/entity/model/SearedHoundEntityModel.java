package com.exdrill.soulsandsorcery.client.render.entity.model;


import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.client.render.entity.animation.SearedHoundEntityAnimation;
import com.exdrill.soulsandsorcery.entity.SearedHoundEntity;
import net.minecraft.block.Blocks;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class SearedHoundEntityModel<T extends HostileEntity> extends SinglePartEntityModel<SearedHoundEntity> {

    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(SoulsAndSorcery.MODID, "seared_hound"), "main");
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart tail;
    private final ModelPart upperBody;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public SearedHoundEntityModel(ModelPart root) {
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.rightLeg = this.root.getChild("rightLeg");
        this.leftLeg = this.root.getChild("leftLeg");
        this.rightArm = this.body.getChild("rightArm");
        this.leftArm = this.body.getChild("leftArm");
        this.head = this.body.getChild("head");
        this.tail = this.body.getChild("tail");
        this.upperBody = this.body.getChild("upperBody");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData modelPartData = meshdefinition.getRoot();

        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.NONE);

        ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -6.0F, -9.0F, 10.0F, 8.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 14.0F, 5.0F));

        ModelPartData upperBody = body.addChild("upperBody", ModelPartBuilder.create().uv(0, 20).cuboid(-6.0F, -8.0F, -12.0F, 12.0F, 11.0F, 9.0F, new Dilation(0.25F)), ModelTransform.pivot(-1.0F, 0.0F, -3.0F));

        ModelPartData tail = body.addChild("tail", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -2.0F, 3.0F));

        ModelPartData rightArm = body.addChild("rightArm", ModelPartBuilder.create().uv(8, 40).cuboid(-2.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.5F, 2.0F, -11.0F));

        ModelPartData leftArm = body.addChild("leftArm", ModelPartBuilder.create().uv(0, 40).cuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, 2.0F, -11.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(32, 0).cuboid(-3.0F, -3.0F, -4.0F, 6.0F, 6.0F, 4.0F, new Dilation(0.0F))
                .uv(32, 40).cuboid(-3.0F, -9.0F, -2.0F, 6.0F, 6.0F, 0.0F, new Dilation(0.0F))
                .uv(33, 20).cuboid(-1.5F, -0.0156F, -7.0F, 3.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -1.5F, -15.0F));

        ModelPartData rightLeg = root.addChild("rightLeg", ModelPartBuilder.create().uv(24, 40).cuboid(-2.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.5F, 16.0F, 6.0F));

        ModelPartData leftLeg = root.addChild("leftLeg", ModelPartBuilder.create().uv(16, 40).cuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, 16.0F, 6.0F));

        return TexturedModelData.of(meshdefinition, 64, 64);
    }


    @Override
    public void setAngles(SearedHoundEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        float k = Math.min((float)entity.getVelocity().lengthSquared() * 100F, 2.0F);
        this.head.pitch = headPitch * 0.017453292F;
        this.head.yaw = headYaw * 0.017453292F;
        this.updateAnimation(entity.runningAnimationState, SearedHoundEntityAnimation.RUNNING, animationProgress, k);
        this.updateAnimation(entity.diggingAnimationState, SearedHoundEntityAnimation.DIGGING, animationProgress);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }


}