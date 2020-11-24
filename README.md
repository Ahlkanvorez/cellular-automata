# Cellular Automata

A configurable webapp simulating Conway's Game of Life, and the
Day & Night variation.

This started as a weekend project to explore cellular automata. There
are multiple implementations of the internal data structures, with the
option to switch between them at runtime, since I found it interesting
to see how a few simple backends performed.

The webapp is written in ClojureScript, using Quil for graphics, and
Re-Frame, Reagent, & ReactJS for the UI. Deployments are to AWS S3,
using CloudFront.

## Usage

The project setup assumes you have [Leiningen](https://leiningen.org/)
installed:

- Run `lein figwheel` in your terminal.
- Wait for a while until you see `Successfully compiled "resources/public/js/main.js"`.
- Open [localhost:3449](http://localhost:3449) in your browser.

## Deployment

The deployment pipeline in `bitbucket-pipelines.yml` produces an
optimized build to deploy to S3 via `lein do clean, cljsbuild once optimized`.

## License

BSD 3-Clause License

Copyright (c) 2020, Robert Mitchell
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.

3. Neither the name of the copyright holder nor the names of its
   contributors may be used to endorse or promote products derived from
   this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.