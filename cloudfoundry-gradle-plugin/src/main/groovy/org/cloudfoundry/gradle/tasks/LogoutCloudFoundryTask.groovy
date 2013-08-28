/*
 * Copyright 2012 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cloudfoundry.gradle.tasks

import org.cloudfoundry.client.lib.tokens.TokensFile
import org.gradle.api.tasks.TaskAction

/**
 * Logs out and removes access token.
 */
class LogoutCloudFoundryTask extends AbstractCloudFoundryTask {

    LogoutCloudFoundryTask() {
        super()
        description = 'Logs out of the target Cloud Foundry platform'
    }

    @TaskAction
    void logout() {
        log "Logging out"

        TokensFile tokensFile = new TokensFile()
        tokensFile.removeToken(target.toURI())
    }
}
