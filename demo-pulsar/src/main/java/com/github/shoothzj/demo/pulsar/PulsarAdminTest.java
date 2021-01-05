package com.github.shoothzj.demo.pulsar;

import com.github.shoothzj.demo.pulsar.constant.PulsarConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.client.admin.PulsarAdminException;
import org.apache.pulsar.client.admin.Topics;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.impl.conf.ClientConfigurationData;

import java.util.List;

/**
 * @author hezhangjian
 */
@Slf4j
public class PulsarAdminTest {

    public static void main(String[] args) throws PulsarClientException, PulsarAdminException {
        final ClientConfigurationData clientConfigData = new ClientConfigurationData();
        final PulsarAdmin pulsarAdmin = new PulsarAdmin(PulsarConstant.SERVICE_HTTP_URL, clientConfigData);
        final List<String> tenants = pulsarAdmin.tenants().getTenants();
        System.out.println(tenants);
        pulsarAdmin.close();
    }

}
