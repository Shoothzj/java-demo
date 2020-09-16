package com.github.shoothzj.demo.sb.kubectl.controller;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.apis.AppsV1Api;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.apis.ExtensionsV1beta1Api;
import io.kubernetes.client.models.V1ContainerStatus;
import io.kubernetes.client.models.V1Deployment;
import io.kubernetes.client.models.V1DeploymentList;
import io.kubernetes.client.models.V1ObjectMeta;
import io.kubernetes.client.models.V1OwnerReference;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.models.V1PodSpec;
import io.kubernetes.client.models.V1PodStatus;
import io.kubernetes.client.models.V1Service;
import io.kubernetes.client.models.V1ServiceList;
import io.kubernetes.client.models.V1StatefulSet;
import io.kubernetes.client.models.V1StatefulSetList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hezhangjian
 */
@Slf4j
@RestController
public class KubectlController {

    private AtomicInteger atomicInteger = new AtomicInteger();

    private ApiClient client;

    private CoreV1Api api;

    private AppsV1Api appApi;

    private ExtensionsV1beta1Api beatApi;

    {
        try {
            client = ClientBuilder.kubeconfig(
                    KubeConfig.loadKubeConfig(new FileReader("/opt/sh/secret/config"))).build();
        } catch (IOException e) {
            log.error("init cluster client exception ", e);
        }

        // the CoreV1Api loads default api-client from global configuration.
        api = new CoreV1Api(client);
        appApi = new AppsV1Api(client);
        beatApi = new ExtensionsV1beta1Api(client);
    }

    @GetMapping(path = "/debug/kubectl", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void debugZk() throws ApiException {
        log.info("start debug kubectl");
        V1PodList v1PodList = api.listNamespacedPod("default", true, null, null, null,
                null, null, null, null, null);
        for (V1Pod item : v1PodList.getItems()) {
            V1ObjectMeta metadata = item.getMetadata();
            V1OwnerReference ownerReference = metadata.getOwnerReferences().get(0);
            V1PodSpec spec = item.getSpec();
            V1PodStatus status = item.getStatus();
            V1ContainerStatus containerStatus = status.getContainerStatuses().get(0);
            log.info("v1 pod item is {}", item);
        }
    }

    @GetMapping(path = "/debug/kubectl/service", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void debugZkContinue() throws ApiException {
        V1ServiceList serviceList = api.listNamespacedService("default", null, null, null, null, null, null, null, null, null);
        for (V1Service item : serviceList.getItems()) {
            log.info("v1 service item is {}", item);
        }
    }

    @PostMapping(path = "/debug/kubectl/deploy", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void debugZkCreateDeploy() throws ApiException {
        V1Deployment v1Deployment = new V1Deployment();
        appApi.createNamespacedDeployment("default", null, null, null, null);
    }

    @GetMapping(path = "/debug/kubectl/deploy", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void debugZkDeploy() throws ApiException {
        V1DeploymentList v1DeploymentList = appApi.listNamespacedDeployment("default", null, null, null, null, null, null, null, null, null);
        for (V1Deployment item : v1DeploymentList.getItems()) {
            log.info("v1 deploy item is {}", item);
        }
    }

    @GetMapping(path = "/debug/kubectl/state", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void debugZkState() throws ApiException {
        V1StatefulSetList v1StatefulSetList = appApi.listNamespacedStatefulSet("default", null, null, null, null, null, null, null, null, null);
        for (V1StatefulSet item : v1StatefulSetList.getItems()) {
            log.info("v1 state item is {}", item);
        }
    }

    @GetMapping(path = "/debug/kubectl/{servicename}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void debugZkCreate() {
        log.info("start debug kubectl");
    }

}
