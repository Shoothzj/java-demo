function main()
{
    gradle $2
    cp $1/build/libs/ttbb.jar .
    docker build . -t ttbb/java-demo:$2
}

cd "$(dirname "$0")"
main "$1" "$2"