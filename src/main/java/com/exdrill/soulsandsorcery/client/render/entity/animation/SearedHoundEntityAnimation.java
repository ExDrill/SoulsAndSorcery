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
    public static final Animation DIGGING;

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
        DIGGING = Builder.create(3.0F)
                .addBoneAnimation(
                        "body",
                        new Transformation(
                                Targets.ROTATE,
                                new Keyframe(0.0F, AnimationHelper.method_41829(0F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.16F, AnimationHelper.method_41829(-5F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.32F, AnimationHelper.method_41829(15F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.44F, AnimationHelper.method_41829(22.5F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.68F, AnimationHelper.method_41829(22.5F, 0F, 5F), Interpolations.field_37885),
                                new Keyframe(1.12F, AnimationHelper.method_41829(22.5F, 0F, -5F), Interpolations.field_37885),
                                new Keyframe(1.56F, AnimationHelper.method_41829(22.5F, 0F, 5F), Interpolations.field_37885),
                                new Keyframe(2.0F, AnimationHelper.method_41829(22.5F, 0F, -5F), Interpolations.field_37885),
                                new Keyframe(2.44F, AnimationHelper.method_41829(22.5F, 0F, 5F), Interpolations.field_37885),
                                new Keyframe(2.88F, AnimationHelper.method_41829(22.5F, 0F, -5F), Interpolations.field_37885),
                                new Keyframe(3.0F, AnimationHelper.method_41829(0F, 0F, 0F), Interpolations.field_37885)
                        )
                )
                .addBoneAnimation(
                        "rightArm",
                        new Transformation(
                                Targets.ROTATE,
                                new Keyframe(0.0F, AnimationHelper.method_41829(0F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.2F, AnimationHelper.method_41829(-50F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.52F, AnimationHelper.method_41829(22.5F, 0F, 0F), Interpolations.field_37885),

                                new Keyframe(0.68F, AnimationHelper.method_41829(45F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.92F, AnimationHelper.method_41829(-67.5F, -15F, 15F), Interpolations.field_37885),
                                new Keyframe(1.12F, AnimationHelper.method_41829(0F, 0F, 0F), Interpolations.field_37885),

                                new Keyframe(1.56F, AnimationHelper.method_41829(45F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(1.8F, AnimationHelper.method_41829(-67.5F, -15F, 15F), Interpolations.field_37885),
                                new Keyframe(0.0F, AnimationHelper.method_41829(0F, 0F, 0F), Interpolations.field_37885),

                                new Keyframe(0.0F, AnimationHelper.method_41829(45F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.0F, AnimationHelper.method_41829(-67.5F, -15F, 15F), Interpolations.field_37885),
                                new Keyframe(0.0F, AnimationHelper.method_41829(0F, 0F, 0F), Interpolations.field_37885)
                        )
                )
                .addBoneAnimation(
                        "rightArm",
                        new Transformation(
                                Targets.TRANSLATE,
                                new Keyframe(0.0F, AnimationHelper.method_41823(0F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.68F, AnimationHelper.method_41823(0F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(0.8F, AnimationHelper.method_41823(0F, 5F, -2F), Interpolations.field_37885),
                                new Keyframe(0.92F, AnimationHelper.method_41823(0F, 2F, -3F), Interpolations.field_37885),
                                new Keyframe(1.12F, AnimationHelper.method_41823(0F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(1.56F, AnimationHelper.method_41823(0F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(1.68F, AnimationHelper.method_41823(0F, 5F, -2F), Interpolations.field_37885),
                                new Keyframe(1.8F, AnimationHelper.method_41823(0F, 2F, -3F), Interpolations.field_37885),
                                new Keyframe(2.0F, AnimationHelper.method_41823(0F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(2.44F, AnimationHelper.method_41823(0F, 0F, 0F), Interpolations.field_37885),
                                new Keyframe(2.56F, AnimationHelper.method_41823(0F, 5F, -2F), Interpolations.field_37885),
                                new Keyframe(2.68F, AnimationHelper.method_41823(0F, 2F, -3F), Interpolations.field_37885),
                                new Keyframe(2.88F, AnimationHelper.method_41823(0F, 0F, 0F), Interpolations.field_37885)
                        )
                )
                .addBoneAnimation(
                        "leftArm",
                        new Transformation(
                                Targets.ROTATE,
                                new Keyframe(0.0F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Interpolations.field_37885),
                                new Keyframe(0.2F, AnimationHelper.method_41829(-50.0F, 0.0F, 0.0F), Interpolations.field_37885),
                                new Keyframe(0.52F, AnimationHelper.method_41829(22.5F, 0.0F, 0.0F), Interpolations.field_37885),

                                new Keyframe(1.12F, AnimationHelper.method_41829(45.0F, 0.0F, 0.0F), Interpolations.field_37885),
                                new Keyframe(1.36F, AnimationHelper.method_41829(-67.5F, 15.0F, -15.0F), Interpolations.field_37885),
                                new Keyframe(1.56F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Interpolations.field_37885),

                                new Keyframe(2.0F, AnimationHelper.method_41829(45.0F, 0.0F, 0.0F), Interpolations.field_37885),
                                new Keyframe(2.24F, AnimationHelper.method_41829(-67.5F, 15.0F, -15.0F), Interpolations.field_37885),
                                new Keyframe(2.44F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Interpolations.field_37885)
                        )
                )
                .addBoneAnimation(
                        "leftArm",
                        new Transformation(
                                Targets.TRANSLATE,
                                new Keyframe(0.0F, AnimationHelper.method_41823(0.0F, 0.0F, 0.0F), Interpolations.field_37885),
                                new Keyframe(1.12F, AnimationHelper.method_41823(0.0F, 0.0F, 0.0F), Interpolations.field_37885),
                                new Keyframe(1.24F, AnimationHelper.method_41823(0.0F, 5.0F, -2.0F), Interpolations.field_37885),
                                new Keyframe(1.36F, AnimationHelper.method_41823(0.0F, 2.0F, -3.0F), Interpolations.field_37885),
                                new Keyframe(1.56F, AnimationHelper.method_41823(0.0F, 0.0F, 0.0F), Interpolations.field_37885),
                                new Keyframe(2.0F, AnimationHelper.method_41823(0.0F, 0.0F, 0.0F), Interpolations.field_37885),
                                new Keyframe(2.12F, AnimationHelper.method_41823(0.0F, 5.0F, -2.0F), Interpolations.field_37885),
                                new Keyframe(2.24F, AnimationHelper.method_41823(0.0F, 2.0F, -3.0F), Interpolations.field_37885),
                                new Keyframe(2.44F, AnimationHelper.method_41823(0.0F, 0.0F, 0.0F), Interpolations.field_37885)
                        )
                )
                .addBoneAnimation(
                        "head",
                        new Transformation(
                                Targets.ROTATE,
                                new Keyframe(0.0F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Interpolations.field_37885),
                                new Keyframe(0.16F, AnimationHelper.method_41829(-15.0F, 0.0F, 0.0F), Interpolations.field_37885),
                                new Keyframe(0.32F, AnimationHelper.method_41829(-15.0F, 0.0F, 0.0F), Interpolations.field_37885),
                                new Keyframe(0.44F, AnimationHelper.method_41829(15.0F, 0.0F, 0.0F), Interpolations.field_37885),
                                new Keyframe(0.68F, AnimationHelper.method_41829(30.0F, 0.0F, 10.0F), Interpolations.field_37885),
                                new Keyframe(1.12F, AnimationHelper.method_41829(30.0F, 0.0F, -10.0F), Interpolations.field_37885),
                                new Keyframe(1.56F, AnimationHelper.method_41829(30.0F, 0.0F, 10.0F), Interpolations.field_37885),
                                new Keyframe(2.0F, AnimationHelper.method_41829(30.0F, 0.0F, -10.0F), Interpolations.field_37885),
                                new Keyframe(2.44F, AnimationHelper.method_41829(30.0F, 0.0F, 10.0F), Interpolations.field_37885),
                                new Keyframe(2.88F, AnimationHelper.method_41829(30.0F, 0.0F, -10.0F), Interpolations.field_37885),
                                new Keyframe(3.0F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Interpolations.field_37885)
                        )
                )
                .addBoneAnimation(
                        "rightLeg",
                        new Transformation(
                                Targets.ROTATE,
                                new Keyframe(0.0F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Interpolations.field_37884),
                                new Keyframe(0.0F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Interpolations.field_37884),
                                new Keyframe(0.0F, AnimationHelper.method_41829(0.0F, 0.0F, 7.5F), Interpolations.field_37884),
                                new Keyframe(0.0F, AnimationHelper.method_41829(0.0F, 0.0F, 7.5F), Interpolations.field_37884),
                                new Keyframe(0.0F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Interpolations.field_37884)

                        )
                )
                .addBoneAnimation(
                        "leftLeg",
                        new Transformation(
                                Targets.ROTATE,
                                new Keyframe(0.0F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Interpolations.field_37884),
                                new Keyframe(0.0F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Interpolations.field_37884),
                                new Keyframe(0.0F, AnimationHelper.method_41829(0.0F, 0.0F, -7.5F), Interpolations.field_37884),
                                new Keyframe(0.0F, AnimationHelper.method_41829(0.0F, 0.0F, -7.5F), Interpolations.field_37884),
                                new Keyframe(0.0F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Interpolations.field_37884)
                        )
                )
                .build();
    }
}
