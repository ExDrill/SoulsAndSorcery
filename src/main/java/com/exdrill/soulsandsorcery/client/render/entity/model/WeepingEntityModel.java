package com.exdrill.soulsandsorcery.client.render.entity.model;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.entity.WeepingEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.Identifier;

public class WeepingEntityModel<T extends HostileEntity> extends EntityModel<WeepingEntity> {
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(SoulsAndSorcery.MODID, "bawling"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart leftArm;
	private final ModelPart rightArm;

	public WeepingEntityModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.leftArm = body.getChild("leftArm");
		this.rightArm = body.getChild("rightArm");
	}

	public static TexturedModelData createBodyLayer() {
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(0, 22).cuboid(-3.0F, -10.0F, -2.0F, 6.0F, 10.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -12.0F, -5.0F, 10.0F, 12.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -10.0F, 0.0F));

		ModelPartData leftArm = body.addChild("leftArm", ModelPartBuilder.create().uv(26, 22).cuboid(-3.0F, -1.0F, -2.0F, 3.0F, 10.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -9.0F, 0.0F, 0.0F, 0.0F, 0.0F));

		ModelPartData rightArm = body.addChild("rightArm", ModelPartBuilder.create().uv(26, 22).mirrored().cuboid(0.0F, -1.0F, -2.0F, 3.0F, 10.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(3.0F, -9.0F, 0.0F, 0.0F, 0.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		this.body.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}

	@Override
	public void setAngles(WeepingEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	}
}