/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.kafka.common.security.ssl;

import org.apache.kafka.common.network.Mode;

import javax.net.ssl.SSLEngine;
import java.security.KeyStore;
import java.util.Map;
import java.util.Set;

/**
 * Plugin interface for allowing creation of SSLEngine object in a custom way.
 * Example: You want to use custom way to load your key material and trust material needed for SSLContext.
 */
public interface SslEngineFactory {

    /**
     * Create a new SSLEngine object.
     *
     * @param mode                   Whether to use client or server mode.
     * @param peerHost               The peer host to use. This is used in client mode if endpoint validation is enabled.
     * @param peerPort               The peer port to use. This is a hint and not used for validation.
     * @param endpointIdentification Endpoint identification algorithm for client mode.
     * @return The new SSLEngine.
     */
    SSLEngine createSslEngine(Mode mode, String peerHost, int peerPort, String endpointIdentification);

    /**
     * Returns true if SSLEngine needs to be rebuilt.
     *
     * @param nextConfigs       The configuration we want to use.
     * @return                  True only if this builder should be rebuilt.
     */
    boolean shouldBeRebuilt(Map<String, Object> nextConfigs);

    /**
     * Returns the names of configs that may be reconfigured.
     */
    Set<String> reconfigurableConfigs();

    /**
     * Returns existing configuration.
     */
    Map<String, Object> configs();

    /**
     * Returns keystore.
     * @return
     */
    KeyStore keystore();

    /**
     * Returns truststore.
     * @return
     */
    KeyStore truststore();
}
