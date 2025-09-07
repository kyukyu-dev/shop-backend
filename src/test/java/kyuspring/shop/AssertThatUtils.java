package kyuspring.shop;

import org.assertj.core.api.AssertProvider;
import org.assertj.core.api.Assertions;
import org.springframework.test.json.JsonPathValueAssert;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertThatUtils {
    public static Consumer<AssertProvider<JsonPathValueAssert>> notNull() {
        return value -> assertThat(value).isNotNull();
    }

    public static Consumer<AssertProvider<JsonPathValueAssert>> equalsTo(String comparator) {
        return value -> Assertions.assertThat(value).isEqualTo(comparator);
    }

    public static Consumer<AssertProvider<JsonPathValueAssert>> equalsTo(Long comparator) {
        return value -> Assertions.assertThat(value).isEqualTo(comparator);
    }

    public static Consumer<AssertProvider<JsonPathValueAssert>> equalsTo(Integer comparator) {
        return value -> Assertions.assertThat(value).isEqualTo(comparator);
    }

    public static Consumer<AssertProvider<JsonPathValueAssert>> equalsTo(Boolean comparator) {
        return value -> Assertions.assertThat(value).isEqualTo(comparator);
    }
}
