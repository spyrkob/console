Starts a local web server listening to http://localhost:3000. Please make sure to configure the allowed origins of the HTTP management endpoint.

- Standalone mode

        /core-service=management/management-interface=http-interface:list-add(name=allowed-origins,value=http://localhost:3000)
        reload

- Domain mode
 
        /host=master/core-service=management/management-interface=http-interface:list-add(name=allowed-origins,value=http://localhost:3000)
        reload --host=master

To install and run HAL use

```
npm install hal-console
[PORT=dddd] node hal-console
```

See also https://hal.github.io/documentation/get-started/#npm
