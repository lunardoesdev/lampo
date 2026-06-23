
This is a sample android project that is very easy to build yourself.
The only requirement is mise (mise.jdx.dev)

## install using obtainium
Install using Obtainium: obtainium://app/https://github.com/lunardoesdev/lampo


when you have mise installed and ready, run:
```
mise run apk
```

signing process is manual (adjust paths):
```
apksigner sign --ks ~/kon/lunatic-release-key.jks --ks-key-alias lunatic-alias lampo-v1.1.apk
```

that will install dependencies including java, gradle, android sdk and all
particular neede components, and build the app.
Your apk is in ./build/outputs/apk/debug.


## screenshots

![./screenshot1.png](./screenshot1.png)
![./screenshot2.png](./screenshot2.png)

# the following is just devlog, reading is optional.


Initially my idea was to find a ready-to-use solution, but I didn’t find one.

## architecture

- mise has android-sdk package.
- mise has nice tasks system

So I decided to use that to get development environment with gradle, android sdk, java and all other needed components handled automatically so that I can just build/run the project.

<!-- -->
## installing android sdk

so, mise has android-sdk package we can use in our setup. But we need to install components specific for our project. thats where —package_file has a good use:

```text
sdkmanager --package_file=android-sdk-packages.txt 
```

and in out android-sdk-packages.txt:

```text
platform-tools
build-tools;35.0.0
platforms;android-35
ndk;26.1.10909125
cmake;3.22.1
```

<!-- -->
## tmpfs quirk on my system:

I have about 2-3gb of /tmp tmpfs. That is sometimes not enough for android’s sdkmanager to do it’s job. Thats why I redefine TMPDIR variable and create/remove that temp directory in the process of installing android deps.

<!-- -->
## so how to use my project:

you can insall mise using instructions on [mise.jdx.dev](http://mise.dev)

After that just run

```text
mise run build
```

That’s it. No need for anything else.

that will install dependencies including java, gradle, android sdk and all         
particular neede components, and build the app.  
Your apk is in ./build/outputs/apk/debug.

<!-- -->
