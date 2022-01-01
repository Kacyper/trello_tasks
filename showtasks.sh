#!/usr/bin/env bash


fail() {
  echo "Error: Something's wrong"
}

end() {
  echo "Done!"
}

run() {
  open -a firefox http://localhost:8080/crud/v1/task/getTasks
#  open http://localhost:8080/crud/v1/task/getTasks
}


if [ "./runcrud.sh" ]; then
  run
  end

else
  fail
fi