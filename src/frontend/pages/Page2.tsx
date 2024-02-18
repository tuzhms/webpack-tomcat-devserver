import * as React from 'react'
import {injectPage} from '../lib/app'
import Button from "../lib/Button";

const Page2 = () => <div>
    <h1>Page 2</h1>
    <p>This is page 2</p>
    <Button href="../index.jsp" label="index.jsp"/>
</div>

injectPage(<Page2/>)