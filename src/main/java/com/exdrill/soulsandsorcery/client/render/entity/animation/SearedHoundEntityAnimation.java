package com.exdrill.soulsandsorcery.client.render.entity.animation;

import net.minecraft.client.render.entity.animation.Animation.Builder;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import net.minecraft.client.render.entity.animation.Transformation.Interpolations;
import net.minecraft.client.render.entity.animation.Transformation.Targets;

public class SearedHoundEntityAnimation {

    public static final Animation RUNNING;

    static {
        RUNNING = Builder
                .create(0.72F)
                .addBoneAnimation(
                        "body",
                        new Transformation(
                                Targets.ROTATE,
                                new Keyframe(0.0F, AnimationHelper.method_41829(0F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.08F, AnimationHelper.method_41829(10F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.16F, AnimationHelper.method_41829(15F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.28F, AnimationHelper.method_41829(15F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.4F, AnimationHelper.method_41829(-15F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.56F, AnimationHelper.method_41829(-15F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.72F, AnimationHelper.method_41829(0F, 0F, 0F), Interpolations.field_37885)
                        )
                )
                .addBoneAnimation(
                        "body",
                        new Transformation(
                                Targets.TRANSLATE,
                                new Keyframe(0.0F, AnimationHelper.method_41823(0, 0, 0), Interpolations.field_37884),
                                new Keyframe(0.4F, AnimationHelper.method_41823(0, 3, 0), Interpolations.field_37884),
                                new Keyframe(0.72F, AnimationHelper.method_41823(0, 0, 0), Interpolations.field_37884)
                        )
                )
                .addBoneAnimation(
                        "rightArm",
                        new Transformation(
                                Targets.ROTATE,
                                new Keyframe(0.0F, AnimationHelper.method_41829(0F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.12F, AnimationHelper.method_41829(22.5F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.2F, AnimationHelper.method_41829(22.5F, 0F, 0F), Interpolations.field_37884),
                                new Keyframe(0.32F, AnimationHelper.method_41829(-22.5F, 0F, 0F), Interpolations.field_37884),
                                new Keyframe(0.4F, AnimationHelper.method_41829(-45F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.56F, AnimationHelper.method_41829(-45F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.72F, AnimationHelper.method_41829(0F, 0F, 0F), Interpolations.field_37885)
                        )
                )
                .addBoneAnimation(
                        "leftArm",
                        new Transformation(
                                Targets.ROTATE,
                                new Keyframe(0.0F, AnimationHelper.method_41829(0F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.12F, AnimationHelper.method_41829(22.5F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.2F, AnimationHelper.method_41829(22.5F, 0F, 0F), Interpolations.field_37884),
                                new Keyframe(0.32F, AnimationHelper.method_41829(-22.5F, 0F, 0F), Interpolations.field_37884),
                                new Keyframe(0.4F, AnimationHelper.method_41829(-45F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.56F, AnimationHelper.method_41829(-45F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.72F, AnimationHelper.method_41829(0F, 0F, 0F), Interpolations.field_37885)
                        )
                )
                .addBoneAnimation(
                        "rightLeg",
                        new Transformation(
                                Targets.ROTATE,
                                new Keyframe(0.0F, AnimationHelper.method_41829(0F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.12F, AnimationHelper.method_41829(-22.5F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.2F, AnimationHelper.method_41829(-22.5F, 0F, 0F), Interpolations.field_37884),
                                new Keyframe(0.32F, AnimationHelper.method_41829(22.5F, 0F, 0F), Interpolations.field_37884),
                                new Keyframe(0.4F, AnimationHelper.method_41829(45F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.56F, AnimationHelper.method_41829(45F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.72F, AnimationHelper.method_41829(0F, 0F, 0F), Interpolations.field_37885)
                        )
                )
                .addBoneAnimation(
                        "leftLeg",
                        new Transformation(
                                Targets.ROTATE,
                                new Keyframe(0.0F, AnimationHelper.method_41829(0F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.12F, AnimationHelper.method_41829(-22.5F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.2F, AnimationHelper.method_41829(-22.5F, 0F, 0F), Interpolations.field_37884),
                                new Keyframe(0.32F, AnimationHelper.method_41829(22.5F, 0F, 0F), Interpolations.field_37884),
                                new Keyframe(0.4F, AnimationHelper.method_41829(45F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.56F, AnimationHelper.method_41829(45F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.72F, AnimationHelper.method_41829(0F, 0F, 0F), Interpolations.field_37885)
                        )
                )
                .addBoneAnimation(
                        "head",
                        new Transformation(
                                Targets.ROTATE,
                                new Keyframe(0.0F, AnimationHelper.method_41829(0F, 0F, 0F), Interpolations.field_37884),
                                new Keyframe(0.08F, AnimationHelper.method_41829(15F, 0F, 0F), Interpolations.field_37884),
                                new Keyframe(0.12F, AnimationHelper.method_41829(35F, 0F, 0F), Interpolations.field_37884),
                                new Keyframe(0.32F, AnimationHelper.method_41829(22.5F, 0F, 0F), Interpolations.field_37884),
                                new Keyframe(0.4F, AnimationHelper.method_41829(0F, 0F, 0F), Interpolations.field_37884),
                                new Keyframe(0.52F, AnimationHelper.method_41829(-10F, 0F, 0F), Interpolations.field_37884),
                                new Keyframe(0.72F, AnimationHelper.method_41829(0F, 0F, 0F), Interpolations.field_37884)
                        )
                )
                .addBoneAnimation(
                        "head",
                        new Transformation(
                                Targets.TRANSLATE,
                                new Keyframe(0.0F, AnimationHelper.method_41823(0F, 0F, 0F), Interpolations.field_37884),
                                new Keyframe(0.04F, AnimationHelper.method_41823(0F, 0F, -3F), Interpolations.field_37884),
                                new Keyframe(0.72F, AnimationHelper.method_41823(0F, 0F, 0F), Interpolations.field_37884)
                        )
                )
                .looping()
                .build();
    }
}
