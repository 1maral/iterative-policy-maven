## Iterative Policy Evaluation Algorithm

Iteratively calculates state-values from an environment $(k + 1)$ times with the goal of approaching an optimal policy. Implementation based on Chapter 4 of *Reinforcement Learning: An Introduction* (Sutton & Barto, 2020).

### Authors

- Maral Bat-Erdene
- Sara Jaljaa

---

### Policy Iteration (for estimating $\pi \approx \pi_*$)

1. **Initialization**

&ensp;&ensp;&ensp;&ensp;&ensp; $V(s) \in \mathbb{R}$ and $\pi(s) \in \mathcal{A}(s)$
arbitrarily for all $s \in \mathcal{S}: V(terminal) \doteq 0$

2. **Policy Evaluation**

&ensp;&ensp;&ensp;&ensp;&ensp; Loop:

&ensp;&ensp;&ensp;&ensp;&ensp; $\Delta \leftarrow 0$

&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; Loop for each $s \in \mathcal{S}:$

&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; $v \leftarrow V(s)$

&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; $V(s) \leftarrow \sum_{s',r} p(s', r | s, \pi(s))[r + \gamma V(s')]$

&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; $\Delta \leftarrow$ $max(\Delta, | v - V(s)|)$

&ensp;&ensp;&ensp;&ensp;&ensp; until $\Delta < \theta$ (a small positive number determining the accuracy of estimation)

3. **Policy Improvement**

&ensp;&ensp;&ensp;&ensp;&ensp; $policy-stable \leftarrow true$

&ensp;&ensp;&ensp;&ensp;&ensp; For each $s \in \mathcal{S}:$

&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; $old-action \leftarrow \pi (s)$

&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; $\pi (s) \leftarrow argmax_{a} \sum_{s',r} p(s', r | s, a)[r + \gamma V(s')]$

&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; If $old-action \neq \pi (s)$, then $policy-stable \leftarrow false$

&ensp;&ensp;&ensp;&ensp;&ensp; If $policy-stable$, then stop and return
$V \approx v_{\*}$ and $\pi \approx \pi_{\*}$; else go to 2

---

### Output

$k = 1$
  |        |        |        |        |
  | ------ | ------ | ------ | ------ |
  |  $0.0$ | $-1.0$ | $-1.0$ | $-1.0$ |
  | $-1.0$ | $-1.0$ | $-1.0$ | $-1.0$ |
  | $-1.0$ | $-1.0$ | $-1.0$ | $-1.0$ |
  | $-1.0$ | $-1.0$ | $-1.0$ | $0.0$  |

$k = 2$
  |        |        |        |        |
  | ------ | ------ | ------ | ------ |
  | $0.0$  | $-1.8$ | $-2.0$ | $-2.0$ |
  | $-1.8$ | $-2.0$ | $-2.0$ | $-2.0$ |
  | $-2.0$ | $-2.0$ | $-2.0$ | $-1.8$ |
  | $-2.0$ | $-2.0$ | $-1.8$ | $0.0$  |

$k = 3$
  |        |        |        |        |
  | ------ | ------ | ------ | ------ |
  | $0.0$  | $-2.4$ | $-2.9$ | $-3.0$ |
  | $-2.4$ | $-2.9$ | $-3.0$ | $-2.9$ |
  | $-2.9$ | $-3.0$ | $-2.9$ | $-2.4$ |
  | $-3.0$ | $-2.9$ | $-2.4$ | $0.0$  |

$k = 4$
  |        |        |        |        |
  | ------ | ------ | ------ | ------ |
  | $0.0$  | $-3.1$ | $-3.8$ | $-4.0$ |
  | $-3.1$ | $-3.7$ | $-3.9$ | $-3.8$ |
  | $-3.8$ | $-3.9$ | $-3.7$ | $-3.1$ |
  | $-4.0$ | $-3.8$ | $-3.1$ | $0.0$  |

$k = 5$
  |        |        |        |        |
  | ------ | ------ | ------ | ------ |
  | $0.0$  | $-3.6$ | $-4.7$ | $-4.9$ |
  | $-3.6$ | $-4.5$ | $-4.8$ | $-4.7$ |
  | $-4.7$ | $-4.8$ | $-4.5$ | $-3.6$ |
  | $-4.9$ | $-4.7$ | $-3.6$ | $0.0$  |

$k = 6$
  |        |        |        |        |
  | ------ | ------ | ------ | ------ |
  | $0.0$  | $-4.2$ | $-5.5$ | $-5.8$ |
  | $-4.2$ | $-5.2$ | $-5.6$ | $-5.5$ |
  | $-5.5$ | $-5.6$ | $-5.2$ | $-4.2$ |
  | $-5.8$ | $-5.5$ | $-4.2$ | $0.0$  |

$k = 7$
  |        |        |        |        |
  | ------ | ------ | ------ | ------ |
  | $0.0$  | $-4.7$ | $-6.3$ | $-6.7$ |
  | $-4.7$ | $-5.9$ | $-6.4$ | $-6.3$ |
  | $-6.3$ | $-6.4$ | $-5.9$ | $-4.7$ |
  | $-6.7$ | $-6.3$ | $-4.7$ | $0.0$  |

$k = 8$
  |        |        |        |        |
  | ------ | ------ | ------ | ------ |
  | $0.0$  | $-5.2$ | $-7.0$ | $-7.5$ |
  | $-5.2$ | $-6.5$ | $-7.1$ | $-7.0$ |
  | $-7.0$ | $-7.1$ | $-6.5$ | $-5.2$ |
  | $-7.5$ | $-7.0$ | $-5.2$ | $0.0$  |

$k = 9$
  |        |        |        |        |
  | ------ | ------ | ------ | ------ |
  | $0.0$  | $-5.7$ | $-7.7$ | $-8.2$ |
  | $-5.7$ | $-7.2$ | $-7.8$ | $-7.7$ |
  | $-7.7$ | $-7.8$ | $-7.2$ | $-5.7$ |
  | $-8.2$ | $-7.7$ | $-5.7$ | $0.0$  |

$k = 10$
  |        |        |        |        |
  | ------ | ------ | ------ | ------ |
  | $0.0$  | $-6.1$ | $-8.3$ | $-9.0$ |
  | $-6.1$ | $-7.7$ | $-8.4$ | $-8.3$ |
  | $-8.3$ | $-8.4$ | $-7.7$ | $-6.1$ |
  | $-9.0$ | $-8.3$ | $-6.1$ | $0.0$  |
