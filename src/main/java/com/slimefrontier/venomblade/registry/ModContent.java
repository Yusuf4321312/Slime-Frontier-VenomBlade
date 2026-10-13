package com.slimefrontier.venomblade.registry;

import com.slimefrontier.venomblade.VenombladeMod;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public final class ModContent {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(VenombladeMod.MOD_ID);
    private static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS =
            DeferredRegister.create(Registries.ARMOR_MATERIAL, VenombladeMod.MOD_ID);
    private static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, VenombladeMod.MOD_ID);

    private static final Holder<ArmorMaterial> VENOM_HUNTER_MATERIAL = ARMOR_MATERIALS.register(
            "venom_hunter",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), defense -> {
                        defense.put(ArmorItem.Type.BOOTS, 2);
                        defense.put(ArmorItem.Type.LEGGINGS, 5);
                        defense.put(ArmorItem.Type.CHESTPLATE, 7);
                        defense.put(ArmorItem.Type.HELMET, 3);
                        defense.put(ArmorItem.Type.BODY, 4);
                    }),
                    12,
                    SoundEvents.ARMOR_EQUIP_IRON,
                    () -> Ingredient.of(Items.IRON_INGOT),
                    List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("minecraft", "iron"))),
                    0.5F,
                    0.0F
            )
    );

    public static final Supplier<SwordItem> VENOM_FANG = ITEMS.register("venom_fang", () -> new SwordItem(
            Tiers.IRON,
            new Item.Properties()
                    .rarity(Rarity.RARE)
                    .attributes(SwordItem.createAttributes(Tiers.IRON, 4, -2.2F))
    ));

    public static final Supplier<ArmorItem> VENOM_HUNTER_HELMET = ITEMS.register("venom_hunter_helmet", () ->
            new ArmorItem(
                    VENOM_HUNTER_MATERIAL,
                    ArmorItem.Type.HELMET,
                    new Item.Properties()
                            .rarity(Rarity.UNCOMMON)
                            .durability(ArmorItem.Type.HELMET.getDurability(15))
            )
    );

    private static final Supplier<CreativeModeTab> MAIN_TAB = CREATIVE_TABS.register("main", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.venomblade.main"))
                    .icon(() -> new ItemStack(VENOM_FANG.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(VENOM_FANG.get());
                        output.accept(VENOM_HUNTER_HELMET.get());
                    })
                    .build()
    );

    private ModContent() {
    }

    public static void register(IEventBus modEventBus) {
        ARMOR_MATERIALS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_TABS.register(modEventBus);
    }
}
