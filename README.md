# shared-library

## send email 

### How to revoke

```

notifyEmail.notifyEmail "$BUILD_STATUS", "$notifier"

``` 

#### arguments

- `BUILD_STATUS`: Use `currentBuild.currentResult` to get current task's result.

[Reference](https://stackoverflow.com/a/63905769/12951895)

- `notifier`: Address to receive email you can add **cc** or **bcc** and use **','** to separate them.
