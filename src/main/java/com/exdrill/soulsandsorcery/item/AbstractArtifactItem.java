package com.exdrill.soulsandsorcery.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
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
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.soulsandsorcery.when_used").formatted(Formatting.GRAY));
        if (soulUsage == 1) {
            tooltip.add(Text.literal(" ").append(Text.literal(soulUsage * -1 + " ")).append(Text.translatable("tooltip.soulsandsorcery.soul_usage.singular")).formatted(Formatting.AQUA));
        } else {
            tooltip.add(Text.literal(" ").append(Text.literal(soulUsage * -1 + " ")).append(Text.translatable("tooltip.soulsandsorcery.soul_usage.plural")).formatted(Formatting.AQUA));
        }
        tooltip.add(Text.literal(""));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
