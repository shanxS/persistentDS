Experimenting with https://www.cs.cmu.edu/~sleator/papers/another-persistence.pdf
Impl is mosty toy with no test cases, just to get a feel of things.

This branch impl BST for partial persistence using fat node method as described in 2.2 of above mentioned paper.

For Node copying method detailed in 2.3, its pretty much the same thing except that instead of saving all versions
in same node, we limit to, say N, versions per node. After which we copy this node and its predecessor if necessary.
This reduces time to search an item in given version, but uses more space. I dont plan to impl it