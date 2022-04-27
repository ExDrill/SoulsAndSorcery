package com.exdrill.soulsandsorcery.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AbstractArtifactItem extends Item {
    private final int soulUsage;

    public AbstractArtifactItem(int soulUsage, Settings settings) {
        super(settings);
        this.soulUsage = soulUsage;
    }

    /*
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (((LivingEntityAccess)user).getSouls() >= soulUsage) {
            ItemStack itemStack = user.getStackInHand(hand);
            itemStack.damage(1, user, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            ((LivingEntityAccess)user).addSouls(soulUsage * -1);
            return TypedActionResult.success(user.getStackInHand(hand));
        }
        else {
            if (world.isClient) {
                MinecraftClient.getInstance().inGameHud.addChatMessage(MessageType.GAME_INFO, new TranslatableText("gameplay.not_enough_souls"), UUID.randomUUID()); **IMPORTANT**
            }
            return TypedActionResult.fail(user.getStackInHand(hand));
        }
    }
    */


    @Override
    public boolean isUsedOnRelease(ItemStack stack) {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new LiteralText(""));
        tooltip.add(new TranslatableText("tooltip.soulsandsorcery.when_used").formatted(Formatting.GRAY));
        if (soulUsage == 1) {
            tooltip.add(new LiteralText(" ").append(new LiteralText(soulUsage * -1 + " ")).append(new TranslatableText("tooltip.soulsandsorcery.soul_usage.singular")).formatted(Formatting.AQUA));
        } else {
            tooltip.add(new LiteralText(soulUsage * -1 + " ").append(new TranslatableText("tooltip.soulsandsorcery.soul_usage.plural")).formatted(Formatting.AQUA));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
