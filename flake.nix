{
  description = "A dev environment for java";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs/nixos-unstable";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { nixpkgs, flake-utils, ... }: 
    flake-utils.lib.eachDefaultSystem (system:
    let
      pkgs = nixpkgs.legacyPackages."${system}";
    in {
      devShell = pkgs.mkShell {
        packages = with pkgs; [
          kotlin
          gradle
        ];
      };

      apps = {
        gradle = {
          type = "app";
          program = "${pkgs.writeScript "bunnylang-build" ''
            #!${pkgs.bash}/bin/bash
            JAVA_HOME="${pkgs.jdk17}"
            ${pkgs.gradle_8}/bin/gradle -Dorg.gradle.java.home=${pkgs.jdk17}/lib/openjdk "$@"
          ''}";
        };
        build = {
          type = "app";
          program = "${pkgs.writeScript "bunnylang-build" ''
            #!${pkgs.bash}/bin/bash
            ${pkgs.gradle_8}/bin/gradle -Dorg.gradle.java.home=${pkgs.jdk17}/lib/openjdk build "$@"
          ''}";
        };
        run = {
          type = "app";
          program = "${pkgs.writeScript "bunnylang-run" ''
            #!${pkgs.bash}/bin/bash
            ${pkgs.gradle_8}/bin/gradle -Dorg.gradle.java.home=${pkgs.jdk17}/lib/openjdk run "$@"
          ''}";
        };
        test = {
          type = "app";
          program = "${pkgs.writeScript "bunnylang-test" ''
            #!${pkgs.bash}/bin/bash
            ${pkgs.gradle_8}/bin/gradle -Dorg.gradle.java.home=${pkgs.jdk17}/lib/openjdk test "$@"
          ''}";
        };
      };
    }
  );
}
