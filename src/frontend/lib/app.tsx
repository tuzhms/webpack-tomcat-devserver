import * as React from 'react'
import {createRoot} from 'react-dom/client'

export const injectPage = (page: React.ReactNode) => createRoot(document.getElementById('app')).render(page)
