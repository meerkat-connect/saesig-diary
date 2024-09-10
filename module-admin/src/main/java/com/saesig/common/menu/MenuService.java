package com.saesig.common.menu;

import com.saesig.global.menu.ResourceItem;
import com.saesig.global.menu.ResourceMapper;
import com.saesig.global.menu.ResourceNode;
import com.saesig.global.menu.ResourceTree;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuService {
    private final ResourceMapper resourceMapper;
    private ResourceTree resourceTree = null;
    private final WebInvocationPrivilegeEvaluator webInvocationPrivilegeEvaluator;

    public String menuPrint() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String context = request.getContextPath();

        StringBuilder sb = new StringBuilder();
        // 트리구조 형성
        ResourceTree resourceTree = getResourceTree("ADMIN");

        //depth == 1
        ResourceNode root = resourceTree.getRoot();
        String curMenuid = "";
        String upprMenuid = "";

        for (ResourceNode resourceNode : root.getChildNodes()) {
            ResourceItem node1 = resourceNode.getData();

            if (node1.isVisible()) {
                if (node1.isDirectory()) {
                    sb
                        .append("<div class=\"pcoded-navigatio-lavel\">")
                        .append(node1.getName())
                        .append("</div>");
                } else {
                    String mkey = "1";
                    sb
                        .append("<a class=\"d-block pcoded-navigatio-lavel\" href=\"javascript:goMenu('")
                        .append(addContextToUrl(context, node1.getUrl()))
                        .append("','")
                        .append(mkey).append("')\">\n")
                        .append(node1.getName())
                        .append("</a>");
                }

                // depth == 2
                if(resourceNode.hasVisibleChildren()) {
                    sb.append("<ul class=\"pcoded-item pcoded-left-item\">");
                    for (ResourceNode resourceNode2 : resourceNode.getChildNodes()) {
                        ResourceItem node2 = resourceNode2.getData();
                        String mkey = "1";

                        if (resourceNode2.hasVisibleChildren()) {
                            sb.append("<li class=\"pcoded-hasmenu ");
                            //equals curmenuid =>active,equals uppermenuid => active pcoded-trigger
                            sb.append("\">\n");
                        } else {
                            sb.append("<li>");
                        }

                        if (node2.isDirectory()) {
                            sb.append("<a href=\"javascript:void(0)\">");
                        } else {
                            sb.append("<a href=\"javascript:goMenu('").append(addContextToUrl(context, node2.getUrl())).append("','").append(mkey).append("')\">\n");
                        }

                        String iconClass = node2.getStyleClass();
                        if (StringUtils.isEmpty(iconClass)) {
                            //set default value
                            iconClass = "icon-hash";
                        }
                        sb
                            .append("<span class=\"pcoded-micon\"><i class=\"feather ")
                            .append(iconClass)
                            .append("\"></i></span><span class=\"pcoded-mtext\">")
                            .append(node2.getName())
                            .append("</span></a>\n");

                        //depth == 3
                        if(resourceNode2.hasVisibleChildren()) {
                            sb.append("<ul class=\"first-depth-submenu pcoded-submenu\">\n");
                            for (ResourceNode resourceNode3 : resourceNode2.getChildNodes()) {
                                ResourceItem node3 = resourceNode3.getData();
                                sb.append("<li class=\"");
                                if (resourceNode3.hasVisibleChildren()) {
                                    sb.append("pcoded-hasmenu ");
                                }
                                sb.append("\">\n");

                                if (node3.isDirectory()) {
                                    sb.append("<a href=\"javascript:void(0)\">");
                                } else {
                                    sb.append("<a href=\"javascript:goMenu('").append(addContextToUrl(context, node3.getUrl())).append("','").append(mkey).append("')\">\n");
                                }
                                sb.append("<span class=\"pcoded-mtext\">").append(node3.getName()).append("</span></a>\n");

                                if(resourceNode3.hasVisibleChildren()) {
                                    sb.append("<ul class=\"pcoded-submenu\">\n");
                                    for (ResourceNode resourceNode4 : resourceNode3.getChildNodes()) {
                                        ResourceItem node4 = resourceNode4.getData();

                                        sb.append("    <li class=\"");
                                        /*if (menuItem4.getMenuid().equals(menuItem.getMenuid())) {
                                            sb.append("active ");
                                        }*/
                                        sb.append("\">\n");

                                        if (node4.isDirectory()) {
                                            sb.append("<a href=\"javascript:void(0)\">");
                                        } else {
                                            sb.append("<a href=\"javascript:goMenu('").append(addContextToUrl(context, node4.getUrl())).append("','").append(mkey).append("')\">\n");
                                        }
                                        sb.append("<span class=\"pcoded-mtext\">").append(node4.getName()).append("</span></a></li>\n");
                                    }
                                    sb.append("</ul>");
                                }
                                sb.append("</li>");
                            }
                            sb.append("</ul>");
                        }
                        sb.append("</li>");
                    }
                    sb.append("</ul>");
                }
            }
        }

        // depth == 3
        // <ul class="pcoded-submenu"> </ul>
        // sb.append("<li class=""> <a href=${url}><span class="pcoded-mtext">${resource_name}</span></a></li>")

        // depth >= 4

        return sb.toString();
    }

    public ResourceItem getResourceItemBy(ResourceNode node, String url, String httpMethod) {
        ResourceItem returnResource = null;

        if(node == null) return null;

        if (node != null) {
            ResourceItem resourceItem = node.getData();
            if (url.equals(resourceItem.getUrl()) && httpMethod.equals(resourceItem.getHttpMethod())) {
                returnResource = resourceItem;
            } else {
                for (ResourceNode childNode : node.getChildNodes()) {
                    returnResource = getResourceItemBy(childNode, url, httpMethod);
                    if (returnResource != null) break;
                }
            }
        }

        return returnResource;
    }

    private String addContextToUrl(String context, String url) {
        if (!url.startsWith("http")) {
            return context + url;
        } else {
            return url;
        }
    }

    public ResourceTree getResourceTree(String resourceCategory) {

        if (resourceTree != null) return resourceTree;

        // 자원 유형에 따른 목록 조회
        List<ResourceItem> resources = resourceMapper.findAll();
        return makeResourcetree(resources);
    }

    private ResourceTree makeResourcetree(List<ResourceItem> resources) {
        if (resourceTree == null) {
            resourceTree = new ResourceTree();
        }

        synchronized (resourceTree) {
            for (ResourceItem resource : resources) {
                ResourceNode resourceNode = new ResourceNode();
                resourceNode.setData(resource);

                if (resource.getId().equals(0L)) {
                    resourceTree.setRoot(resourceNode);
                } else {
                    ResourceNode parentNode = getParentNodeById(resource.getUpperId());
                    if (parentNode != null) {
                        parentNode.addChild(resourceNode);
                    }
                }
            }
        }
        return resourceTree;
    }

    private ResourceNode getParentNodeById(Long upperId) {
        return getParentNodeById(resourceTree.getRoot(), upperId);
    }

    private ResourceNode getParentNodeById(ResourceNode resourceNode, Long menuId) {
        if (resourceNode != null) {
            ResourceItem data = resourceNode.getData();
            if (menuId.equals(data.getId())) {
                return resourceNode;
            } else {
                for (ResourceNode node : resourceNode.getChildNodes()) {
                    ResourceNode parentNodeById = getParentNodeById(node, menuId);
                    if (parentNodeById != null) {
                        return parentNodeById;
                    }
                }
            }
        }
        return null;
    }

}
