#!/usr/bin/bash

# CONFIG VARS (PULLED FROM ENV VARS)
max_failures=-1
max_sched_tasks=-1

if [[ -z "${SYNC_HEALTHCHECK_MAX_IDLE_CHECK_FAILURES}" ]]; then
  max_failures=-1
else
  max_failures="${SYNC_HEALTHCHECK_MAX_IDLE_CHECK_FAILURES}"
fi

if [[ -z "${SYNC_HEALTHCHECK_MAX_SCHEDULED_TASKS}" ]]; then
  max_sched_tasks=-1
else
  max_sched_tasks="${SYNC_HEALTHCHECK_MAX_SCHEDULED_TASKS}"
fi

# METRICS ENDPOINT CHECK
curl -s -o /dev/null localhost:10142/metrics
if [[ $? -ne "0" ]]; then
	echo "No metrics available, exiting UNHEALTHY"
	exit 1
fi

games_created=$(curl -s localhost:10142/metrics | grep -v "#" | grep "games_started_counter" | awk '{ print $2 }')
games_created=$(echo ${games_created%.*})

scheduled_tasks_gauge=$(curl -s localhost:10142/metrics | grep -v "#" | grep "scheduled_tasks_gauge" | awk '{ print $2 }')
scheduled_tasks_gauge=$(echo ${scheduled_tasks_gauge%.*})

if [[ $scheduled_tasks_gauge -gt $max_sched_tasks ]] && [[ $max_sched_tasks -ne -1  ]]; then
	echo "Too many tasks scheduled, exiting UNHEALTHY"
	exit 1
fi

if [ ! -f "failure_count" ]; then
    echo "0" > failure_count
fi
if [ ! -f "scheduled_tasks_gauge" ]; then
    echo "0" > scheduled_tasks_gauge
fi
if [ ! -f "games_created" ]; then
    echo "0" > games_created
fi

failure_count=$(cat failure_count)

prev_scheduled_tasks_gauge=$(cat scheduled_tasks_gauge)
scheduled_tasks_diff=$(expr $scheduled_tasks_gauge - $prev_scheduled_tasks_gauge)

prev_games_created=$(cat games_created)
games_created_diff=$(expr $games_created - $prev_games_created)

# GAMES CREATED CHECK
if [[ $games_created_diff -eq 0 ]]; then # no difference in number of games created
	failure_count=$(expr $failure_count + 1)
	echo "No new games created since last check, incrementing failure_count to $failure_count"
else
	failure_count=0
fi

if [[ $failure_count -gt $max_failures ]] && [[ $max_failures -ne -1  ]]; then
	echo "Too many failures, exiting UNHEALTHY"
	exit 1
fi

echo $failure_count > failure_count
echo $scheduled_tasks_gauge > scheduled_tasks_gauge
echo $games_created > games_created

# EXIT HEALTHY
exit 0
