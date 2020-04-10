# 说明

该项目分两部分

- 后台服务框架(svc)
- 前端应用框架(app)

## 后台服务框架

### 获取用户信息

网关将用户的orgId、userId放到了用户request的header里，可以在controller中添加下列参数获取orgId、userId
```
@RequestHeader(value = "X-YADA-ORG-ID") String orgId
@RequestHeader(value = "X-YADA-USER-ID") String userId
```

### 网关配置说明

- 参数说明

  | 参数      | 说明           |
  |---------|--------------|
  | svcIp   | 本项目中svc的访问IP |
  | svcPort | 本项目中svc的访问端口 |
  | svcId   | 网关中定义的svc的ID |
  | svcPath | 本项目中svc的上下文  |

- 配置模版
  ```
  uri: http://${svcIp}:${svcPort}
  predicates:
    - Svc=/api,${svcId}
  filters:
    - AuthApi
    - RewritePath=/api/${svcId},/${svcPath}
  ```

### 项目网关配置

将服务添加到网关中,需要在网关服务中添加下列配置
```
uri: http://localhost:3012
predicates:
  - Svc=/api,msp
filters:
  - AuthApi
  - RewritePath=/api/msp,/msp
```

## 前端应用框架

### 网关配置说明

- 参数说明

  | 参数      | 说明           |
  |---------|--------------|
  | appIp   | 本项目中app的访问IP |
  | appPort | 本项目中app的访问端口 |
  | appPath | 本项目中app的上下文  |

- 配置模版
  ```
  uri: http://${appIp}:${appPort}
  predicates:
    - App=/${appId}
  filters:
    - Auth
    - RewritePath=/${appId},/${appPath}
  ```

### 项目网关配置

将应用添加到网关中,需要在网关服务中添加下列配置
```
uri: http://localhost:3012
predicates:
  - App=/msp
filters:
  - Auth
  - RewritePath=/msp,/msp/app
```


## 运行访问

- 编译应用

  ```
  cd svc
  gradle buildAll
  ```

- 启动服务

  ```
  gradle bootRun
  ```

- 配置权限

  访问网关服务到权限配置管理端将服务端，将服务端资源访问权限赋给角色

- 访问应用

  浏览器打开下列地址访问应用
  ```
  http://localhost:8080/msp
  ```
