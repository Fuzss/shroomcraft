package fuzs.shroomcraft.common.world.entity.animal;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.level.biome.Biome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class MobVariantUtil {

    private MobVariantUtil() {
        // NO-OP
    }

    public static Holder<MobVariant> getSpawnVariant(Registry<MobVariant> registry, Holder<Biome> biome, TagKey<MobVariant> fallbackTag, RandomSource randomSource) {
        return getRandomSpawnVariant(registry, (Holder<MobVariant> holder) -> {
            return holder.value().biomes().contains(biome);
        }).or(() -> getRandomSpawnVariant(registry, (Holder<MobVariant> holder) -> {
            return holder.is(fallbackTag);
        })).or(() -> registry.getRandom(randomSource)).orElseThrow();
    }

    public static Optional<Holder<MobVariant>> getRandomSpawnVariant(Registry<MobVariant> registry, Predicate<Holder<MobVariant>> filter) {
        return Optional.ofNullable(registry.listElements()
                .filter(filter)
                .collect(Collectors.collectingAndThen(Collectors.toCollection(ArrayList::new),
                        (List<Holder.Reference<MobVariant>> list) -> {
                            Collections.shuffle(list);
                            return !list.isEmpty() ? list.getFirst() : null;
                        })));
    }

    public static Holder<MobVariant> getNextVariant(Registry<MobVariant> registry, Holder<MobVariant> current, TagKey<MobVariant> groupTag) {
        List<Holder.Reference<MobVariant>> variants = registry.listElements()
                .filter((Holder.Reference<MobVariant> holder) -> holder.is(groupTag))
                .toList();
        if (variants.isEmpty()) {
            variants = registry.listElements().toList();
        }

        int index = -1;
        for (int i = 0; i < variants.size(); i++) {
            if (variants.get(i).value() == current.value()) {
                index = i;
                break;
            }
        }

        return variants.get((index + 1) % variants.size());
    }

    public static class VariantGroupData extends AgeableMob.AgeableMobGroupData {
        public final Holder<MobVariant> variant;

        public VariantGroupData(Holder<MobVariant> variant) {
            this(variant, true);
        }

        public VariantGroupData(Holder<MobVariant> variant, boolean shouldSpawnBaby) {
            super(shouldSpawnBaby);
            this.variant = variant;
        }
    }
}
