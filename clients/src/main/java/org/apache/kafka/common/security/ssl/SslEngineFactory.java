package org.apache.kafka.common.security.ssl;

import org.apache.kafka.common.network.Mode;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import java.security.KeyStore;
import java.util.Map;
import java.util.Set;

public interface SslEngineFactory {

    /**
     * Creates a new SSLEngine object.
     *
     * @param mode      Whether to use client or server mode.
     * @param peerHost  The peer host to use. This is used in client mode if endpoint validation is enabled.
     * @param peerPort  The peer port to use. This is a hint and not used for validation.
     * @param endpointIdentification Endpoint identification algorithm for client mode.
     * @return          The new SSLEngine.
     */
    SSLEngine create(Mode mode, String peerHost, int peerPort, String endpointIdentification);

    /**
     * Returns the currently used configurations by this engine.
     * @return
     */
    Map<String, Object> currentConfigs();

    /**
     * Returns the reconfigurable configs used by this engine.
     * @return
     */
    Set<String> reconfigurableConfigs();

    /**
     * Returns true if this engine needs to be rebuilt.
     *
     * @param nextConfigs       The configuration we want to use.
     * @return                  True only if this builder should be rebuilt.
     */
    boolean shouldBeRebuilt(Map<String, Object> nextConfigs);

    /**
     * Returns the {@code KeyStore} for the keystore used by this engine.
     * @return
     */
    KeyStore keystore();

    /**
     * Returns the {@code KeyStore} for the truststore used by this engine.
     * @return
     */
    KeyStore truststore();
}
