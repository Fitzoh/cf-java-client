/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cloudfoundry.reactor.client;

import org.cloudfoundry.QueryParameter;
import org.junit.Test;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public final class QueryBuilderTest {

    @Test
    public void test() {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();

        QueryBuilder.augment(builder, new StubQueryParamsSubClass());

        MultiValueMap<String, String> queryParams = builder.build().encode().getQueryParams();

        assertThat(queryParams).hasSize(5);
        assertThat(queryParams.getFirst("test-single")).isEqualTo("test-value-1");
        assertThat(queryParams.getFirst("test-collection")).isEqualTo("test-value-2,test-value-3");
        assertThat(queryParams.getFirst("test-collection-custom-delimiter")).isEqualTo("test-value-4%20test-value-5");
        assertThat(queryParams.getFirst("test-subclass")).isEqualTo("test-value-6");
        assertThat(queryParams.getFirst("test-override")).isEqualTo("test-value-7");
    }

    public static abstract class StubQueryParams {

        @QueryParameter("test-collection")
        public final List<String> getCollection() {
            return Arrays.asList("test-value-2", "test-value-3");
        }

        @QueryParameter(value = "test-collection-custom-delimiter", delimiter = " ")
        public final List<String> getCollectionCustomDelimiter() {
            return Arrays.asList("test-value-4", "test-value-5");
        }

        @QueryParameter("test-empty")
        public final List<String> getEmpty() {
            return Collections.emptyList();
        }

        @QueryParameter("test-empty-value")
        public final String getEmptyValue() {
            return "";
        }

        @QueryParameter("test-null")
        public final String getNull() {
            return null;
        }

        @QueryParameter("test-single")
        public final String getSingle() {
            return "test-value-1";
        }

        @QueryParameter("test-override")
        abstract String getOverride();

    }

    public static final class StubQueryParamsSubClass extends StubQueryParams {

        @Override
        public String getOverride() {
            return "test-value-7";
        }

        @QueryParameter("test-subclass")
        public String getSubclass() {
            return "test-value-6";
        }

    }

}
