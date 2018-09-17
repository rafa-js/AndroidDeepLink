# AndroidDeepLink
A lightweight Android library to handle deep links.

## Installation

`TODO`


## Example

```
IDeepLinkLauncherFactory deepLinkLauncherFactory = new MyAppLinkLauncherFactory();

DeepLinkHandler deepLinkHandler = DeepLinkHandler.createWithDefaultParser( deepLinkLauncherFactory, "https", "myapp.com" );
deepLinkHandler.setDeepLinkListener( deepLink -> System.out.println( "INFO - Link processed: " + deepLink.getUrl() ) );

String profileLink = "https://myapp.com/profile?id=12345678&alias=hackerman";
deepLinkHandler.handleLink( profileLink );

String postLink = "https://myapp.com/posts?id=87654321&title=hello%20world!&rf=4";
deepLinkHandler.handleLink( postLink );
```

## Components

`TODO`

### DeepLink

`TODO`

### IDeepLinkLauncher

`TODO`

### IDeepLinkLauncherFactory

`TODO`

### IDeepLinkParser

`TODO`

### DefaultDeepLinkParser

`TODO`

### DeepLinkHandler

`TODO`

## Handling your app links

`TODO`

### 1. Define a `IDeepLinkLauncher` for each action.

`TODO`

### 2. Define a `IDeepLinkLauncherFactory` to bind each link to each launcher.

`TODO`

### 3. Create a DeepLinkHandler instance with your custom components.

`TODO`

## License

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
