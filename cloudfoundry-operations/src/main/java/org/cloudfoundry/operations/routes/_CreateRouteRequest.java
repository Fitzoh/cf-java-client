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

package org.cloudfoundry.operations.routes;

import org.cloudfoundry.Nullable;
import org.immutables.value.Value;

/**
 * The request options for the create route operation
 */
@Value.Immutable
abstract class _CreateRouteRequest {

    @Value.Check
    void checkPorts() {
        if (getRandomPort() != null && getRandomPort() && getPort() != null) {
            throw new IllegalStateException("Only one of port and randomPort can be set");
        }
    }

    @Value.Check
    void checkSetup() {
        if (portSet() && hostOrPathSet()) {
            throw new IllegalStateException("Cannot set port/randomPort and hostname/path");
        }
    }

    /**
     * The domain of the route
     */
    abstract String getDomain();

    /**
     * The host name of the route
     */
    @Nullable
    abstract String getHost();

    /**
     * The path of the route
     */
    @Nullable
    abstract String getPath();

    /**
     * The port of the route
     */
    @Nullable
    abstract Integer getPort();

    /**
     * Generate a random port
     */
    @Nullable
    abstract Boolean getRandomPort();

    /**
     * The space to create the route in
     */
    abstract String getSpace();

    private boolean hostOrPathSet() {
        return getHost() != null || getPath() != null;
    }

    private boolean portSet() {
        return (getRandomPort() != null && getRandomPort()) || getPort() != null;
    }

}
